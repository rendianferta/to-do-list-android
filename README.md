# 📱 Task Inventory – Android Task Management App

**Task Inventory** is a task management application built for Android that helps users organize daily tasks based on **status** and **priority categories**.
This project demonstrates practical implementation of **Android development**, **REST API integration**, and **clean architecture (MVVM)**.

🎓 Originally developed as part of a *Mobile Application Development* course project and enhanced as a personal portfolio.

---

## 🚀 Key Features

* Create, view, and manage tasks
* Task lifecycle management:

  * **New → In Progress → Done**
* Category-based prioritization:

  * Normal
  * Urgent
  * Important
* Status-based task filtering
* Confirmation flow before critical actions
* Duration tracking for completed tasks
* REST API integration using **Retrofit**

---

## 🧠 Application Logic & Rules

* Every task starts with status **New**
* A task must be **taken** before it can be completed
* Completed tasks (**Done**) cannot be modified
* Task duration is calculated automatically once finished

This logic ensures clear task progression and prevents invalid state transitions.

---

## 📱 App Screens Overview

### 🔹 Main Screen

* Displays task count by status:

  * New
  * In Progress
  * Done
* Entry point for adding new tasks
* Navigation to task lists

---

### 🔹 Task List Screen

* Displays tasks based on selected status
* Filter tasks by category:

  * Normal
  * Urgent
  * Important
* Displays:

  * Title
  * Category
  * Created time
  * Action buttons (**Take / Done**)
* Completed tasks
