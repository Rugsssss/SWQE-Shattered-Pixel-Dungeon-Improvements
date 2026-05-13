# ELEC5618: Shattered Pixel Dungeon Quality Engineering

This repository contains our group's work for **Assignment 3: Software Quality Engineering**. We are working with **Shattered Pixel Dungeon v3.0.2** to implement and verify code-level quality improvements[cite: 1].

## 📁 Project Structure
The project follows the mandatory folder structure for submission[cite: 1]:
- `target_project/`: The active source code directory.
  - `core/`: Game logic (Target for most Quality Improvements).
  - `desktop/`: Desktop launcher (Contains environment fixes).
- `ELEC5618_{LAB}_{GROUP}_Video.mp4`: (To be added) Final 10-minute demonstration[cite: 1].

---

## 🛠 macOS Setup & Environment Fixes
To prevent the `NullPointerException` and `ExceptionInInitializerError` when running from the IDE, the following patches have been applied to `DesktopLauncher.java`:

1. **Version Bypass:** Hardcoded `Game.version` to `"1.5.0-IDE"` to prevent startup crashes.
2. **Vendor Bypass:** Added a fallback for the `vendor` string to ensure save-file paths resolve correctly.
3. **Mac VM Options:** If you are on Apple Silicon, you **must** add `-XstartOnFirstThread` to your Run Configuration VM Options.

---

## 🎯 Assignment Goals (ISO/IEC 25010)
Our group is tasked with implementing **three** quality-related improvements[cite: 1]:
1. **Predefined Improvement:** (Screenshot Utility)[cite: 1].
2. **Proposed Improvement A:** (Tutor approval required by Week 11)[cite: 1].
3. **Proposed Improvement B:** (Tutor approval required by Week 11)[cite: 1].

Each improvement will be evaluated on **Implementation Quality** (clean code, conventions) and **SQA Activities** (Unit testing, metrics, static analysis)[cite: 1].

---
# Improvement 1: Screenshot Utility
# In-Game Screenshot Utility for Shattered Pixel Dungeon

This feature provides a high-performance, seamless way to capture and save game scenes directly to the repository directory. It was developed to meet rigorous **Software Quality Engineering** standards, ensuring zero impact on gameplay performance.

---

## 🚀 Features

*   **Dedicated Shortcut**: Maped to the `F12` key for instant capture.
*   **Asynchronous Processing**: Background threading ensures the game never "hiccups" or freezes during file IO.
*   **Automated Correction**: Automatically flips pixels vertically to correct OpenGL's bottom-up coordinate inversion.
*   **Cross-Platform Support**: Logic identifies whether the host is Desktop (Mac/Windows) or Mobile (Android) to use the appropriate file-saving strategy.
*   **Memory Safe**: Implements strict resource disposal to prevent RAM bloat during long gaming sessions.

---

## 🛠️ Technical Overview

### Input Interception
The utility "hooks" into the engine's `InputHandler.java`. By overriding the `keyUp` method, the system ensures the screenshot is triggered only once per press and doesn't interfere with existing keybindings.

### The Capture Pipeline
1.  **Buffer Grab**: Captures the current frame buffer from the GPU using LibGDX `ScreenUtils`.
2.  **Transformation**: A pixel-swapping loop reorients the image from OpenGL's coordinate system to standard top-down PNG format.
3.  **Thread Hand-off**: The `Pixmap` data is passed to a background thread to prevent blocking the main Render Thread.
4.  **IO Operations**: The file is saved using a `yyyy-MM-dd_HH-mm-ss` timestamp to the repository root via absolute pathing.

---

## 📋 Rubric Compliance

| Requirement | Implementation Detail |
| :--- | :--- |
| **Keyboard Shortcut** | Handled via `Input.Keys.F12` interception in `InputHandler`. |
| **Save as Image** | Encoded as a `.png` file using `PixmapIO`. |
| **Works During Gameplay** | Implemented at the engine level to ensure availability in all game states. |
| **No Flow Interruption** | Multithreaded architecture keeps file writing off the main game loop. |
| **Hardware Compatibility** | Verified on macOS using `fn + F12` and standard `F12` on Windows. |

---

## 📂 File Locations

*   **Code**: `SPD-classes/src/main/java/com/watabou/utils/Screenshot.java`
*   **Output**: Screenshots are saved to the project root directory (e.g., `/ShatteredPD/screenshot_2026-05-06_14-19-31.png`).

---

## 📖 How to Use

1.  Launch the game through Android Studio or your preferred IDE.
2.  While in-game (or at the menu), press **F12** (Mac users: **fn + F12**).
3.  Check the console output for a "Success" message and the absolute path to your file.
4.  Right-click your project folder and select **"Reload from Disk"** to see the new image in your file tree.

`fix: finalize README documentation for screenshot utility implementation`

---

## 🔄 Collaboration Workflow
- **Branching:** Use `feature/` or `fix/` branches.
- **Commits:** Every change **must** include a git commit message at the end of the message.
- **Debugging:** Do not provide direct answers for code issues; provide clues to help each other learn React/Java better.

## 👥 Group Members
- [Member Name]
- [Member Name]
- [Member Name]
- [Member Name]
- **Martina Therese Reyes** (Software Engineering Accelerated)

---
*Deadline: Sunday, 24 May 2026, 23:59*[cite: 1]

Notes/Logs from devs
May 6, 2026 - 6:32 pm -- CI/CD enabled