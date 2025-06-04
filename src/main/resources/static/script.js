document.addEventListener('DOMContentLoaded', () =>
{
    const addStudentForm = document.getElementById('addStudentForm');
    const studentTableBody = document.getElementById('studentTableBody');
    const subjectsContainer = document.getElementById('subjectsContainer');
    const addSubjectBtn = document.getElementById('addSubjectBtn');
    const sortPerformanceBtn = document.getElementById('sortPerformanceBtn');
    const sortArrow = document.getElementById('sortArrow');

    let currentStudents = [];
    let sortAscending = false;

    const createSubjectInputGroup = () =>
    {
        const subjectGroup = document.createElement('div');
        subjectGroup.classList.add('row', 'g-2', 'mb-3', 'subject-group');
        subjectGroup.innerHTML = `
            <div class="col-md-6">
                <input type="text" class="form-control subject-name" placeholder="Subject Name" required>
            </div>
            <div class="col-md-4">
                <input type="number" class="form-control subject-marks" placeholder="Marks (0-100)" min="0" max="100" required>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-danger btn-sm w-100 remove-subject">Remove</button>
            </div>
        `;

        subjectGroup.querySelector('.remove-subject').addEventListener('click', () =>
        {
            if (subjectsContainer.children.length > 1)
            {
                subjectGroup.remove();
            } else {
                alert('A student must have at least one subject. Cannot remove the last one.');
            }
        });

        return subjectGroup;
    };


    subjectsContainer.querySelector('.remove-subject').addEventListener('click', (event) =>
    {
        if (subjectsContainer.children.length > 1)
        {
            event.target.closest('.subject-group').remove();
        }
        else
        {
            alert('A student must have at least one subject. Cannot remove the last one.');
        }
    });


    addSubjectBtn.addEventListener('click', () =>
    {
        subjectsContainer.appendChild(createSubjectInputGroup());
    });

    const renderStudentsTable = (studentsToRender) =>
    {
        studentTableBody.innerHTML = '<tr><td colspan="5" class="text-center">Loading students...</td></tr>';

        if (studentsToRender.length === 0)
        {
            studentTableBody.innerHTML = '<tr><td colspan="5" class="text-center">No students found. Add some!</td></tr>';
            return;
        }

        studentTableBody.innerHTML = '';
        studentsToRender.forEach(student =>
        {
            const row = studentTableBody.insertRow();
            row.insertCell(0).textContent = student.id;
            row.insertCell(1).textContent = student.name;
            const subjectsText = student.subjects && student.subjects.length > 0? student.subjects.map(subject =>`${subject.name} (${subject.marks})`).join(', '): 'No subjects';
            row.insertCell(2).textContent = subjectsText;
            row.insertCell(3).textContent = student.averageMarks !== undefined ? student.averageMarks.toFixed(2) : 'N/A';
            row.insertCell(4).textContent = student.grade !== undefined ? student.grade : 'N/A';
        });
    };

    const fetchAndDisplayStudents = async () =>
    {
        studentTableBody.innerHTML = '<tr><td colspan="5" class="text-center">Loading students...</td></tr>';

        try {
            const response = await fetch('/students');
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            currentStudents = await response.json();
            renderStudentsTable(currentStudents);
        } catch (error) {
            alert('Could not load student data. Please check your internet connection or try refreshing the page.');
            studentTableBody.innerHTML = '<tr><td colspan="5" class="text-center text-danger">Failed to load students. Please try again later.</td>';
        }
    };

    sortPerformanceBtn.addEventListener('click', () =>
    {
        sortAscending = !sortAscending;
        sortArrow.textContent = sortAscending ? '▲' : '▼';

        currentStudents.sort((a, b) =>
        {
            const avgA = a.averageMarks;
            const avgB = b.averageMarks;

            if (sortAscending)
            {
                return avgA - avgB;
            }
            else
            {
                return avgB - avgA;
            }
        });
        renderStudentsTable(currentStudents);
    });

    addStudentForm.addEventListener('submit', async (event) =>
    {
        event.preventDefault();

        const studentId = document.getElementById('studentId').value.trim();
        const studentName = document.getElementById('studentName').value.trim();

        const subjects = [];
        let isValid = true;

        document.querySelectorAll('.subject-group').forEach(group =>
        {
            const subjectNameInput = group.querySelector('.subject-name');
            const subjectMarksInput = group.querySelector('.subject-marks');
            const name = subjectNameInput.value.trim();
            const marks = parseFloat(subjectMarksInput.value);

            if (!name || isNaN(marks) || marks < 0 || marks > 100)
            {
                isValid = false;
                alert('Please fill in all required fields and ensure marks are between 0 and 100.');
                return;
            }
            subjects.push({ name, marks });
        });

        if (!isValid) {
            alert('Please correct the errors in the form before submitting.');
            return;
        }

        const newStudent =
        {
            id: studentId,
            name: studentName,
            subjects: subjects
        };

        try {
            const response = await fetch('/students',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newStudent)
            });

            if (response.status === 400)
            {
                const errorMessage = await response.text();
                if (errorMessage.includes("already exists")) {
                    alert(`Student ID '${studentId}' already taken. Please choose a different ID.`);
                } else {
                    alert(`Failed to add student: ${errorMessage}. Please try again.`);
                }
            }
            else if (!response.ok)
            {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            else
            {
                alert('Student added successfully!');
                addStudentForm.reset();
                subjectsContainer.innerHTML = '';
                subjectsContainer.appendChild(createSubjectInputGroup());
                fetchAndDisplayStudents();
            }
        } catch (error) {
            alert(`Failed to add student. A server error occurred. Please try again.`);
        }
    });

    fetchAndDisplayStudents();
});