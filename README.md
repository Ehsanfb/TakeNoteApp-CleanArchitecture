# 📝 TakeNoteApp - Clean Architecture

TakeNoteApp is a modern Android notes app built using **Clean Architecture**, **Jetpack Compose**, **Kotlin Coroutines**, and **Room database**. It allows users to create, edit, sort, and manage notes with different colors. This app follows Android development best practices with a clean separation of concerns.

## ✨ Features

- **Take Note Screen**  
  - View a list of all saved notes  
  - Sort notes by title, color, or creation time  
  - Navigate to add a new note  
  - Edit existing notes  

- **Edit Note Screen**  
  - Create new notes or modify existing ones  
  - Choose a custom color for each note  
  - Save or update notes  

## 🧱 Architecture

The app uses **Clean Architecture**, structured into three main layers:

### 🖼️ Presentation Layer (UI)
- Built using **Jetpack Compose**  
- `ViewModel` handles UI logic  
- Uses `StateFlow` to observe and update the UI  
- Navigation between screens using `Navigation Compose`

### 🧠 Domain Layer
- Contains core business logic  
- Includes use cases like:
  - `GetNotesUseCase`  
  - `DeleteNoteUseCase`  
  - `AddNoteUseCase`  
  - `GetNoteByIdUseCase`

### 🗂️ Data Layer
- Uses **Room** for local data storage  
- Repository pattern to interact with Room DAO  
- Note entities are stored and retrieved from a local SQLite DB  

## 🔧 Tech Stack

- **Kotlin**: Programming language  
- **Jetpack Compose**: For building UI  
- **MVVM**: Architecture pattern  
- **Clean Architecture**: For modular structure  
- **Coroutines + Flow**: For async and reactive programming  
- **Room**: For local database  
- **Hilt**: Dependency injection  

## 🚀 Setup

### Prerequisites

- Android Studio (latest stable version)  
- Kotlin 1.5+  
- Gradle 7.0+  

### How to Run

1. Clone this repo  
2. Open in Android Studio  
3. Build and Run the app on an emulator or real device  

---

## 📸 Screenshots

![pic1](https://github.com/user-attachments/assets/077d35bc-0b0b-4b3b-9f22-98a022946e28)
![pic2](https://github.com/user-attachments/assets/a6772458-083b-4390-ab06-837b9bd050cc)



---

## 📚 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Happy noting! 📝
