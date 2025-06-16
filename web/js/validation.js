document.addEventListener("DOMContentLoaded", function (){

    function updateDateTime() {
        const dateElement = document.getElementById('currentDate');
        const timeElement = document.getElementById('currentTime');

        if (dateElement && timeElement) {
            const now = new Date();
            const date = now.toLocaleDateString();
            const time = now.toLocaleTimeString();

            dateElement.textContent = date;
            timeElement.textContent = time;
        }
    }

    // Only set interval if we're on a page with datetime elements
    if (document.getElementById('currentDate')) {
        updateDateTime();
        setInterval(updateDateTime, 1000);
    }

    const emailPattern = /^[\w!#$%&'*+/=?`{|}~^-]+(?:\.[\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$/;
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;

    $('#user-signup-btn').click(function (e) {
        const name = $('#name').val();
        const email = $('#email').val();
        const password = $('#password').val();
        const role = $('select[name="role"]').val();

        if (!name || !email || !password || !role) {
            alert("Please fill all fields!");
            e.preventDefault();
        }

        if (email !== '' && !emailPattern.test(email)) {
            alert("Please enter a valid email!");
            e.preventDefault();
        }

        if (password !== '' && !passwordPattern.test(password)) {
            alert('Password must be at least 8 characters long and include uppercase, lowercase, number, and special character.');
            e.preventDefault();
        }

    });

    $('#user-signin-btn').click(function (e) {
        const email = $('#email').val();
        const password = $('#password').val();

        if (!email || !password) {
            alert("Please fill all fields!");
            e.preventDefault();
        }

        //const emailPattern = /^[\w!#$%&'*+/=?`{|}~^-]+(?:\.[\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$/;

        /*if (email !== '' && !emailPattern.test(email)) {
            alert("Please enter a valid email!");
            e.preventDefault();
        }*/

        //const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;

        /*if (password !== '' && !passwordPattern.test(password)) {
            alert('Password must be at least 8 characters long and include uppercase, lowercase, number, and special character.');
            e.preventDefault();
        }*/

    });

});