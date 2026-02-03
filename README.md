📱 Task Inventory – Android Task Management App

Task Inventory is a task management application built for Android that helps users organize daily tasks based on status and priority categories.
This project demonstrates practical implementation of Android development, REST API integration, and clean architecture (MVVM).

🎓 Originally developed as part of a Mobile Application Development course project and enhanced as a personal portfolio.

🚀 Key Features

Create, view, and manage tasks

Task lifecycle management:

New → In Progress → Done

Category-based prioritization:

Normal

Urgent

Important

Status-based task filtering

Confirmation flow before critical actions

Duration tracking for completed tasks

REST API integration using Retrofit

🧠 Application Logic & Rules

Every task starts with status New

A task must be taken before it can be completed

Completed tasks (Done) cannot be modified

Task duration is calculated automatically once finished

This logic ensures clear task progression and prevents invalid state transitions.

📱 App Screens Overview
🔹 Main Screen

Displays task count by status:

New

In Progress

Done

Entry point for adding new tasks

Navigation to task lists

🔹 Task List Screen

Displays tasks based on selected status

Filter tasks by category:

Normal

Urgent

Important

Shows:

Title

Category

Created time

Action buttons (Take / Done)

Completed tasks display execution duration

🔹 Confirmation Screen

Displays task details

Confirms user action before:

Taking a task

Completing a task

🔹 Add Task Screen

Create new tasks with:

Title

Description

Category

🛠 Tech Stack
Android

Kotlin

Fragment-based navigation

RecyclerView

Navigation Component

MVVM Architecture

Retrofit (REST API Client)

Backend / API

REST API using JSON Server

db.json as mock backend data source

🧱 Architecture Overview

The app follows MVVM (Model–View–ViewModel) architecture to ensure:

Separation of concerns

Scalable and maintainable codebase

Testable business logic




⚙️ How to Run the Project
1️⃣ Run REST API (Backend)

Install JSON Server:

npm install -g json-server


Start the API:

json-server --watch db.json --port 3000

2️⃣ Run Android Application

Open ToDoListAndroid in Android Studio

Make sure Retrofit base URL uses:

http://10.0.2.2:3000


Run the app on an emulator or physical device

🎯 Learning Outcomes

Through this project, I gained experience in:

Android app development with Kotlin

Implementing MVVM architecture

RESTful API consumption

Managing application state and workflows

Building user-friendly task management systems

👨‍💻 Author

Rendi Anferta
📚 Mobile Application Development Project
💼 Android Developer | Full-Stack Enthusiast

📌 Notes

This project uses mock backend data for demonstration purposes

Designed for educational and portfolio use
