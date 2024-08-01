document.addEventListener('DOMContentLoaded', () => {
    const studentsList = document.getElementById('studentsList');
    const studentForm = document.getElementById('studentForm');

    // Fetch all students on page load
    fetchStudents();

    // Event listener for form submission
    studentForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const student = {
            firstName: document.getElementById('firstName').value,
            lastName: document.getElementById('lastName').value,
            email: document.getElementById('email').value,
            dateOfBirth: document.getElementById('dateOfBirth').value,
            age: document.getElementById('age').value,
        };
        saveStudent(student);
    });

    // Function to fetch and display all students
    function fetchStudents() {
        fetch('/api/v1/students')
            .then(response => response.json())
            .then(data => {
                studentsList.innerHTML = '';
                data.forEach(student => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.email}</td>
                        <td>${student.dateOfBirth}</td>
                        <td>${student.age}</td>
                        <td>
                            <button onclick="deleteStudent('${student.email}')">Delete</button>
                        </td>
                    `;
                    studentsList.appendChild(row);
                });
            })
            .catch(error => console.error('Error fetching students:', error));
    }

    // Function to save a new student
    function saveStudent(student) {
        fetch('/api/v1/students/save_student', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(student),
        })
            .then(response => response.text())
            .then(message => {
                alert(message);
                fetchStudents();
                studentForm.reset();
            })
            .catch(error => console.error('Error saving student:', error));
    }

    // Function to delete a student
    window.deleteStudent = function(email) {
        fetch(`/api/v1/students/delete_mapping/${email}`, {
            method: 'DELETE',
        })
            .then(() => {
                alert('Student deleted');
                fetchStudents();
            })
            .catch(error => console.error('Error deleting student:', error));
    };
});