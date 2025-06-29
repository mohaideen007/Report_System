# Reportsystem
🔎 Project Description
The Report System Web Application is a full-stack web platform developed to streamline user registration, report submission, and automated communication via email. This system is particularly designed for organizations, institutions, or academic environments where structured reporting and timely notifications are essential.

The application allows users to register by submitting their details and uploading a file, such as a report document or project submission. Upon successful registration, a confirmation email is automatically sent to the registered email address, acknowledging their participation and ensuring a smooth onboarding experience.

The backend of the application is powered by Spring Boot, and the frontend is built using React.js with Tailwind CSS for styling. The data is securely stored in a MySQL database, and JWT-based authentication is integrated for protected routes and future scalability.

🛠️ Technologies Used
Layer	Technology
Frontend	React.js, Tailwind CSS
Backend	Spring Boot, Java, Spring Security
Database	MySQL
Authentication	JWT (JSON Web Token)
File Upload	Multipart via FormData
Mail Service	JavaMailSender (Gmail SMTP)
Build Tools	Maven
Server Port	8080 (Spring Boot), 5173 (React Dev)

🎯 Key Features
✅ User Registration with username, password, email, and file upload

✅ Email Confirmation sent immediately after successful registration

✅ Spring Security + JWT integrated for API protection

✅ File Handling through Multipart requests

✅ Responsive UI built using React and Tailwind CSS

✅ MySQL Database for storing user data and uploaded file metadata

📁 Modules
User Module

Register user

Store file and user info in DB

Validate input

Email Module

Send confirmation email using SMTP

Configurable message template

Security Module

JWT token generation and validation

User authentication and authorization

🚀 How It Works (Flow)
User opens registration form in the browser.

Fills in details (username, email, password) and uploads a file.

On clicking Register, the data is sent to the Spring Boot backend.

Backend stores the user data in MySQL and sends a confirmation email.

On success, user is redirected to login page.

💡 Future Enhancements


Enable report review workflow (approve/reject)

Send PDF attachment in confirmation mail

Add OTP or verification link for email validation

Schedule automated reminder emails

