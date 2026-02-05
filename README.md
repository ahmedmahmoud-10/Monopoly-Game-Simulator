# Battleship Simulation Engine (Java)

A Java 17+ Battleship simulation engine built for running repeatable strategy experiments at scale. It includes multiple AI player strategies, head-to-head match runners, and CSV exports for analysis and visualization.

This repo is designed to be easy to extend: add new strategies, change rules, adjust board sizes/ship sets, and run large batches of games to compare outcomes.

---

## What’s inside

### Core features
- Complete Battleship game loop (setup → turns → win/draw resolution)
- Multiple AI strategies with distinct play styles
- Experiment runners for head-to-head and randomized matchups
- Metrics collection (wins/losses/draws + per-game stats)
- CSV exports to support graphs, dashboards, and deeper analysis
- Automated tests (JUnit 5 + Gradle)

---

## Strategies included

The engine ships with five built-in strategies:

- **Aggressive** — pressures early by focusing attacks (e.g., heavy attention to a specific region)
- **Greedy** — when it hits, it “chases” nearby cells to finish ships quickly
- **Random** — fully random shots with no memory or adaptation
- **Smart** — random selection with memory (never repeats an attacked cell)
- **Strategic** — patterned scanning (checkerboard-style coverage) to increase hit probability

> Want to add your own? Create a new strategy class that selects the next target coordinate based on game state and prior shots.

---

## Running the project

### Requirements
- Java **17+**
- Gradle

### Run tests
```bash
./gradlew test
