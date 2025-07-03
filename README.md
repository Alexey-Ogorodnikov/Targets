# Targets2 - MVVM Android App

A modern Android application built with MVVM architecture, Jetpack Compose, and Hilt dependency injection.

## Architecture Overview

This project follows the **MVVM (Model-View-ViewModel)** architecture pattern with clean architecture principles:

### 🏗️ Project Structure

```
app/src/main/java/com/alexey/targets2/
├── data/                           # Data Layer
│   ├── local/                      # Local data sources (Room Database)
│   │   ├── AppDatabase.kt
│   │   ├── TaskDao.kt
│   │   └── Converters.kt
│   ├── model/                      # Data models
│   │   └── Task.kt
│   └── repository/                 # Repository implementations
│       ├── TaskRepository.kt
│       └── TaskRepositoryImpl.kt
├── domain/                         # Domain Layer
│   └── usecase/                    # Use cases (Business logic)
│       ├── GetAllTasksUseCase.kt
│       ├── AddTaskUseCase.kt
│       ├── UpdateTaskUseCase.kt
│       ├── DeleteTaskUseCase.kt
│       └── ToggleTaskCompletionUseCase.kt
├── presentation/                   # Presentation Layer
│   ├── component/                  # Reusable UI components
│   │   └── AddTaskDialog.kt
│   ├── navigation/                 # Navigation
│   │   └── AppNavigation.kt
│   ├── screen/                     # UI screens
│   │   └── TaskListScreen.kt
│   ├── state/                      # UI state models
│   │   └── TaskUiState.kt
│   └── viewmodel/                  # ViewModels
│       └── TaskViewModel.kt
├── di/                            # Dependency Injection
│   ├── DatabaseModule.kt
│   └── RepositoryModule.kt
├── ui/                            # UI theme
│   └── theme/
├── MainActivity.kt
└── Targets2Application.kt
```

## 🛠️ Technologies Used

- **Jetpack Compose** - Modern UI toolkit
- **MVVM Architecture** - Clean separation of concerns
- **Hilt** - Dependency injection
- **Room Database** - Local data persistence
- **Kotlin Coroutines & Flow** - Asynchronous programming
- **Navigation Compose** - Screen navigation
- **Material Design 3** - Modern UI components

## 📱 Features

- ✅ **Task Management**: Create, read, update, and delete tasks
- ✅ **Priority Levels**: Low, Medium, High priority tasks
- ✅ **Task Completion**: Mark tasks as completed/incomplete
- ✅ **Filtering**: Toggle between active and completed tasks
- ✅ **Modern UI**: Material Design 3 with Jetpack Compose
- ✅ **Offline Support**: Local database with Room
- ✅ **Clean Architecture**: MVVM with proper separation of concerns

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 36
- Kotlin 2.2.0

### Installation

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or device

## 🏛️ Architecture Details

### Data Layer
- **Room Database**: Local SQLite database with Room ORM
- **Repository Pattern**: Abstracts data sources and provides clean API
- **Data Models**: Entity classes for database storage

### Domain Layer
- **Use Cases**: Business logic operations
- **Repository Interfaces**: Contracts for data operations
- **Domain Models**: Core business entities

### Presentation Layer
- **ViewModels**: Manage UI state and business logic
- **Compose UI**: Declarative UI components
- **State Management**: Reactive state with StateFlow
- **Navigation**: Type-safe navigation with Compose Navigation

### Dependency Injection
- **Hilt Modules**: Provide dependencies
- **Singleton Components**: Database and repository instances
- **ViewModel Injection**: Automatic ViewModel creation

## 📊 Data Flow

1. **UI Action** → User interacts with Compose UI
2. **ViewModel** → Handles business logic and state updates
3. **Use Case** → Executes business operations
4. **Repository** → Manages data sources
5. **Database/API** → Performs data operations
6. **State Update** → UI automatically updates via StateFlow

## 🧪 Testing

The project is set up for testing with:
- **Unit Tests**: JUnit for business logic
- **UI Tests**: Compose testing for UI components
- **Integration Tests**: Repository and database testing

## 📝 Code Style

- **Kotlin Coding Conventions**: Follow official Kotlin style guide
- **Compose Best Practices**: Use proper Compose patterns
- **MVVM Guidelines**: Clear separation between layers
- **Hilt Usage**: Proper dependency injection patterns

## 🔧 Configuration

### Build Configuration
- **Compile SDK**: 36
- **Min SDK**: 24
- **Target SDK**: 36
- **Java Version**: 1.8

### Dependencies
All dependencies are managed through the version catalog in `gradle/libs.versions.toml` for consistent versioning across the project.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.
