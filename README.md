# <p align="center">💎 Lumina Growth 💎</p>
<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-2.1.0-purple?style=for-the-badge&logo=kotlin" alt="Kotlin">
  <img src="https://img.shields.io/badge/Jetpack_Compose-Material_3-blue?style=for-the-badge&logo=jetpackcompose" alt="Compose">
  <img src="https://img.shields.io/badge/Architecture-MVVM_Clean-orange?style=for-the-badge" alt="Architecture">
  <img src="https://img.shields.io/badge/UI/UX-Glassmorphism-pink?style=for-the-badge" alt="UI/UX">
</p>

---

## 🌟 Overview
**Lumina Growth** is an ultra-modern, high-fidelity personal development application built from the ground up to explore the absolute limits of **Jetpack Compose**. Developed as an intensive education project, it serves as a playground for mastering advanced Kotlin features, high-performance custom drawing, and complex animation states in Android.

This isn't just a habit tracker; it's a **living UI showcase** designed to provide a premium, "Apple-level" user experience while maintaining a clean, scalable architectural foundation.

---

## 📸 Visual Showcase

<img width="1080" height="2220" alt="image" src="" />
<img width="1080" height="2220" alt="image" src="" />
<img width="1080" height="2220" alt="image" src="https://github.com/user-attachments/assets/5b0cb2cb-3968-45f7-b829-505022b3d92b" />

<p align="center">
  <img src="https://github.com/user-attachments/assets/6300228e-e899-4a8a-a1ba-9e9f4f3d1b30" width="24%">
  <img src="https://github.com/user-attachments/assets/5b626430-0b79-4667-a308-7b41b406844e" width="24%">
  <img src="https://github.com/user-attachments/assets/a7029171-d775-46e2-a2ca-5ab184981a74" width="24%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/2983f6eb-daf1-4969-a04f-bfad45541176" width="24%">
  <img src="https://github.com/user-attachments/assets/635bef67-b0bb-41d6-853b-a393b1dcfe23" width="24%">
  <img src="https://github.com/user-attachments/assets/90611b7b-bca2-4319-982d-788deb44a0fa" width="24%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/86fcec80-18f6-4bf1-90e9-2b03f0400f81" width="24%">
  <img src="https://github.com/user-attachments/assets/8eadede0-b320-4824-bc41-9416217312d3" width="24%">
  <img src="" width="24%">
</p>

---

## 🚀 "The Wow Factors" (Technical Highlights)

### 🛸 1. Atmospheric Particle Engine
Lumina features a background that feels alive. We implemented a custom **Canvas-based Particle System** where "stardust" floats upwards, interacting with moving **Aurora Blobs**. 
- **Tech**: `rememberInfiniteTransition`, `DrawScope.drawCircle`, real-time delta-time calculations.

### 💎 2. Premium Glassmorphism 2.0
Every card in Lumina isn't just semi-transparent. They feature:
- **Inner Glow**: Custom border gradients that pulse.
- **Backdrop Interaction**: Dynamic transparency that shifts based on the moving background blobs.
- **Blur Simulation**: Fine-tuned opacity layers to create the illusion of frosted glass.

### 📈 3. Neon Mood Analytics (Custom Drawing)
Forget standard charting libraries. We built a custom **Path-based Line Chart** with:
- **Glow Brushes**: Multi-stop linear gradients applied to the stroke.
- **Dynamic Scaling**: Paths that animate and scale based on the historical mood data.
- **Shadow Fills**: Vertical gradients that create a volumetric feel under the data line.

### 🎊 4. Physics-Based Celebration
Completing a task shouldn't be boring. Lumina triggers a **Confetti Burst** using a custom particle physics engine:
- **Gravity Simulation**: Particles have velocity, weight, and fade-over-time properties.
- **Randomization**: Colors and vectors are calculated on-the-fly for a unique "explosion" every time.

---

## 🏗️ Architecture & Flow

### 🧱 MVVM + Clean Architecture
The project follows a strict separation of concerns to ensure the "crazy visuals" don't turn into "spaghetti code".

```mermaid
graph TD
    subgraph UI_Layer [UI Layer - Jetpack Compose]
        Screen[Composables]
        VM[ViewModel - StateFlow]
    end

    subgraph Domain_Layer [Domain Layer - Business Logic]
        Model[Data Models]
    end

    subgraph Data_Layer [Data Layer - Source of Truth]
        Repo[Mock Repositories]
        Mock[MockData Engine]
    end

    Screen -->|Events| VM
    VM -->|UI State| Screen
    VM -->|Requests| Repo
    Repo -->|Data| Model
    Model --> VM
```

### 🌊 User Interaction Flow
```mermaid
sequenceDiagram
    participant User
    |User|->>Dashboard: Views Master Ring
    Dashboard->>Habits: Navigates
    User->>Habits: Toggles "Hydration"
    Habits->>ViewModel: updateHabitStatus(id)
    ViewModel-->>Habits: Emit Success State
    Habits->>Canvas: Trigger Confetti Physics
    Canvas-->>User: Visual Celebration!
```

---

## 📊 MAD Score (Modern Android Development)

| Component | Status | Description |
| :--- | :--- | :--- |
| **Language** | 🟢 **100% Kotlin** | Utilizing Coroutines, Flow, and Functional paradigms. |
| **UI** | 🟢 **100% Compose** | Zero XML layouts. Full Material 3 implementation. |
| **Architecture** | 🟢 **Jetpack MVVM** | Using Lifecycle, ViewModel, and State-centric UI. |
| **Tooling** | 🟢 **Modern Stack** | Version Catalog (TOML), KSP, and Hilt. |

---

## 🛠️ Tech Stack & Credits

- **Kotlin**: Core language.
- **Jetpack Compose**: UI framework.
- **Material 3**: Design system.
- **Coroutines & Flow**: Reactive state management.
- **Navigation Compose**: Type-safe screen routing.
- **Canvas API**: Custom drawing for Charts, Rings, and Particles.
- **Hilt (Ready)**: Dependency injection infrastructure.

---

## 🎓 Learning Outcomes
This project was a deep dive into:
1.  **Mathematical Drawing**: Calculating offsets and paths for custom charts.
2.  **State Hoisting**: Managing complex UI interactions across fragmented screens.
3.  **Performance Optimization**: Running heavy animations and particle systems without dropping frames.
4.  **Modern Brand Design**: Understanding how to translate high-fidelity design prototypes into working Android code.

---

