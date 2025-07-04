# Targets2 - MVVM Android App with SOLID Principles

A modern Android application built with MVVM architecture, Jetpack Compose, Hilt dependency injection, and **SOLID principles** implementation.

## 🎯 **SOLID Principles Implementation**

This project follows all **SOLID principles** with comprehensive documentation in each file:

### **1. Single Responsibility Principle (SRP)**
- **Domain Models**: Pure business logic, no framework dependencies
- **Data Entities**: Database representation with mapping logic
- **Use Cases**: Each handles one specific business operation
- **Repository Interfaces**: Separated read/write operations

### **2. Open/Closed Principle (OCP)**
- **Base Use Case Interfaces**: Extensible without modification
- **Domain Exceptions**: Can be extended with new exception types
- **Parameter Objects**: Can be extended without breaking existing code

### **3. Liskov Substitution Principle (LSP)**
- **Repository Implementation**: Can be substituted for interface
- **Use Case Hierarchy**: UnitUseCase can substitute for UseCase
- **Interface Composition**: TaskRepository combines TaskReader + TaskWriter

### **4. Interface Segregation Principle (ISP)**
- **TaskReader/TaskWriter**: Separate interfaces for read/write operations
- **Use Case Parameters**: Specific parameter classes for each use case
- **Focused Dependencies**: Clients only depend on interfaces they use

### **5. Dependency Inversion Principle (DIP)**
- **Domain-Driven Design**: Domain layer defines contracts
- **Abstraction Dependencies**: Use cases depend on interfaces, not implementations
- **Proper DI Setup**: Hilt modules bind implementations to interfaces

## 🏗️ **Architecture Overview**

This project follows the **MVVM (Model-View-ViewModel)** architecture pattern with clean architecture principles and SOLID design:

### 📁 **Project Structure**

```
app/src/main/java/com/alexey/targets2/
├── domain/                          # Domain Layer (Core Business Logic)
│   ├── model/                       # Domain Models (SRP, DIP)
│   │   └── Task.kt                  # Pure business model
│   ├── repository/                  # Repository Interfaces (ISP, DIP)
│   │   ├── TaskReader.kt            # Read operations only
│   │   ├── TaskWriter.kt            # Write operations only
│   │   └── TaskRepository.kt        # Combined interface (LSP)
│   ├── usecase/                     # Use Cases (SRP, OCP, DIP)
│   │   ├── base/                    # Base Interfaces (OCP, DIP)
│   │   │   └── UseCase.kt           # Extensible use case contracts
│   │   ├── AddTaskUseCase.kt        # Add task business logic
│   │   ├── GetAllTasksUseCase.kt    # Get tasks business logic
│   │   ├── UpdateTaskUseCase.kt     # Update task business logic
│   │   ├── DeleteTaskUseCase.kt     # Delete task business logic
│   │   └── ToggleTaskCompletionUseCase.kt # Toggle completion logic
│   ├── exception/                   # Domain Exceptions (OCP)
│   │   └── TaskException.kt         # Domain-specific error handling
│   └── result/                      # Result Types (LSP)
│       └── Result.kt                # Generic result wrapper
├── data/                            # Data Layer (Implementation)
│   ├── model/                       # Data Models (SRP, DIP)
│   │   └── TaskEntity.kt            # Database entity with mapping
│   ├── local/                       # Local Data Sources
│   │   ├── TaskDao.kt               # Room DAO
│   │   ├── AppDatabase.kt           # Room database
│   │   └── Converters.kt            # Type converters
│   └── repository/                  # Repository Implementations (LSP)
│       └── TaskRepositoryImpl.kt    # Implements domain interfaces
├── presentation/                    # Presentation Layer
│   ├── viewmodel/                   # ViewModels (SRP, DIP)
│   │   └── TaskViewModel.kt         # UI state management
│   ├── screen/                      # UI Screens
│   │   └── TaskListScreen.kt        # Main task list screen
│   ├── component/                   # UI Components
│   │   └── AddTaskDialog.kt         # Add task dialog
│   ├── state/                       # UI State (SRP)
│   │   └── TaskUiState.kt           # UI state models
│   └── navigation/                  # Navigation
│       └── AppNavigation.kt         # Screen navigation
├── di/                             # Dependency Injection (DIP)
│   ├── DatabaseModule.kt            # Database dependencies
│   └── RepositoryModule.kt          # Repository bindings
├── MainActivity.kt                  # Main activity
└── Targets2Application.kt           # Application class
```

## 🛠️ **Technologies Used**

- **Jetpack Compose** - Modern UI toolkit
- **MVVM Architecture** - Clean separation of concerns
- **SOLID Principles** - Object-oriented design principles
- **Hilt** - Dependency injection
- **Room Database** - Local data persistence
- **Kotlin Coroutines & Flow** - Asynchronous programming
- **Navigation Compose** - Screen navigation
- **Material Design 3** - Modern UI components

## 📱 **Features**

- ✅ **Task Management**: Create, read, update, and delete targets
- ✅ **Priority Levels**: Low, Medium, High priority targets
- ✅ **Task Completion**: Mark targets as completed/incomplete
- ✅ **Filtering**: Toggle between active and completed targets
- ✅ **Modern UI**: Material Design 3 with Jetpack Compose
- ✅ **Offline Support**: Local database with Room
- ✅ **Clean Architecture**: MVVM with proper separation of concerns
- ✅ **SOLID Principles**: All five principles properly implemented
- ✅ **Internationalization**: String resources for easy localization
- ✅ **Error Handling**: Domain-specific exceptions and proper error states

## 🚀 **Getting Started**

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 36
- Kotlin 2.2.0

### Installation

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or device

## 🏛️ **Architecture Details**

### **Domain Layer** (Core Business Logic)
- **Domain Models**: Pure business entities with no framework dependencies
- **Repository Interfaces**: Contracts for data operations (ISP, DIP)
- **Use Cases**: Business logic operations (SRP, OCP)
- **Domain Exceptions**: Business-specific error handling (OCP)

### **Data Layer** (Implementation)
- **Room Database**: Local SQLite database with Room ORM
- **Repository Implementation**: Implements domain contracts (LSP)
- **Data Models**: Database entities with mapping to domain models (SRP)
- **Type Converters**: Database type conversions

### **Presentation Layer** (UI)
- **ViewModels**: Manage UI state and business logic (SRP, DIP)
- **Compose UI**: Declarative UI components
- **State Management**: Reactive state with StateFlow
- **Navigation**: Type-safe navigation with Compose Navigation

### **Dependency Injection**
- **Hilt Modules**: Provide dependencies (DIP)
- **Interface Bindings**: Bind implementations to interfaces (DIP)
- **Singleton Components**: Database and repository instances
- **ViewModel Injection**: Automatic ViewModel creation

## 📊 **Data Flow**

1. **UI Action** → User interacts with Compose UI
2. **ViewModel** → Handles business logic and state updates (SRP)
3. **Use Case** → Executes business operations (SRP, DIP)
4. **Repository** → Manages data sources (ISP, DIP)
5. **Database/API** → Performs data operations
6. **State Update** → UI automatically updates via StateFlow

## 🧪 **Testing**

The project is set up for testing with:
- **Unit Tests**: JUnit for business logic (enabled by SOLID principles)
- **UI Tests**: Compose testing for UI components
- **Integration Tests**: Repository and database testing
- **Mock Testing**: Easy mocking with interfaces (DIP)

## 📝 **Code Style & SOLID Documentation**

- **SOLID Comments**: Each file contains detailed comments explaining which SOLID principles are applied
- **Kotlin Coding Conventions**: Follow official Kotlin style guide
- **Compose Best Practices**: Use proper Compose patterns
- **MVVM Guidelines**: Clear separation between layers
- **Hilt Usage**: Proper dependency injection patterns

## 🔧 **Configuration**

### Build Configuration
- **Compile SDK**: 36
- **Min SDK**: 24
- **Target SDK**: 36
- **Java Version**: 1.8

### Dependencies
All dependencies are managed through the version catalog in `gradle/libs.versions.toml` for consistent versioning across the project.

## 📚 **SOLID Principles Documentation**

For detailed explanation of how SOLID principles are implemented, see:
- **SOLID_PRINCIPLES.md** - Comprehensive documentation of SOLID implementation
- **Inline Comments** - Each file contains detailed SOLID principle explanations

## 🎯 **Benefits of SOLID Implementation**

### **Maintainability**
- Clear separation of concerns (SRP)
- Easy to modify individual components
- Reduced coupling between layers (DIP)

### **Testability**
- Interfaces allow easy mocking (DIP)
- Isolated business logic (SRP)
- Clear dependencies (DIP)

### **Extensibility**
- New implementations can be added without modifying existing code (OCP)
- Easy to add new features (OCP)
- Flexible architecture (DIP)

### **Reusability**
- Use cases can be reused across different UI components (SRP)
- Repository interfaces can have multiple implementations (DIP)
- Domain models are framework-agnostic (DIP)

## 🤝 **Contributing**

1. Fork the repository
2. Create a feature branch
3. Make your changes following SOLID principles
4. Add tests if applicable
5. Submit a pull request

## 📄 **License**

This project is licensed under the MIT License.

---

**This project serves as a reference implementation of SOLID principles in Android development with modern architecture patterns.**
