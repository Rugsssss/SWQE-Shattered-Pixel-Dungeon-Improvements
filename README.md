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
1. **Predefined Improvement:** (e.g., Mob Logging, Pathfinding Refactor, or Screenshot Utility)[cite: 1].
2. **Proposed Improvement A:** (Tutor approval required by Week 11)[cite: 1].
3. **Proposed Improvement B:** (Tutor approval required by Week 11)[cite: 1].

Each improvement will be evaluated on **Implementation Quality** (clean code, conventions) and **SQA Activities** (Unit testing, metrics, static analysis)[cite: 1].

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