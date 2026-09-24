# Micro-Pinout Pocket Guide 🔌📱

An offline-first Android reference application built in **Java** and **XML Views** using **MVVM architecture**. It provides a quick and handy pocket guide for checking microcontroller and hardware component pinouts without requiring an internet connection.

---

## ✨ Key Features

- **Component Library & Search**: Instant real-time search across a rich built-in database of microcontrollers and hardware modules.
- **Detailed Pinout View**: Inspect pin numbers, names, descriptions, and functional types.
- **Pin Type Filtering**: Filter pins instantly using category buttons (`All`, `Power`, `Ground`, `Digital`, `GPIO`, `PWM`, `I2C`, `UART`, `Analog`).
- **Interactive Board Diagrams**: Tap board diagrams to open an interactive viewer with pinch-to-zoom and pan capabilities powered by PhotoView.
- **"My Workbench" Inventory**: Star or bookmark components you physically own to keep track of your personal hardware inventory and filter your library to show only your workbench gear.
- **Offline First**: All hardware data (including Raspberry Pi 4, TB6612FNG Motor Driver, and more) is stored locally in JSON assets and parsed efficiently using **Gson**.

---

## 🛠️ Tech Stack & Architecture

- **Language**: Java
- **Architecture**: MVVM (Model-View-ViewModel) using `AndroidViewModel`, `MutableLiveData`, and `Observer` pattern.
- **UI Components**: XML Views, `RecyclerView`, `CardView`, `ConstraintLayout`, and Material Design (`ChipGroup`).
- **JSON Parsing**: Google `Gson` via `assets` folder repository.
- **Image Zooming**: `PhotoView` library (`com.github.chrisbanes:PhotoView`).
- **Local Persistence**: `SharedPreferences` for bookmarking "My Workbench" inventory.

---

## 📂 Project Structure

```text
com.example.micro_pinoutpocketguide/
├── MainActivity.java        # Home screen with search & Workbench filter
├── MainViewModel.java       # ViewModel managing component state & filtering
├── HardwareRepository.java  # Parses local JSON assets using Gson
├── ComponentAdapter.java    # RecyclerView adapter for hardware components
├── PinAdapter.java          # RecyclerView adapter for pinout listings
├── DetailActivity.java      # Pinout detail view with category filters
├── ImageViewerActivity.java # Pinch-to-zoom viewer for board diagrams
├── FavoritesManager.java    # Manages "My Workbench" saved bookmarks
├── HardwareComponent.java   # Component POJO model
└── Pin.java                 # Pin POJO model
```

---

## 🚀 Getting Started

1. Clone or download the repository.
2. Open the project in **Android Studio**.
3. Sync project with Gradle files.
4. Run the app on an Android emulator or physical device (minSdk 24).
