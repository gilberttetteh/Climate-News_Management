# Climate News Management System

The **Climate News Management System** is a Java-based application designed to streamline the organization and management of climate change-related news articles. It provides a user-friendly graphical interface and powerful tools for adding, searching, sorting, and displaying news articles. This project aims to raise awareness and foster informed discussions on climate change, promoting proactive actions.

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Technical Details](#technical-details)
4. [System Requirements](#system-requirements)
5. [Installation](#installation)
6. [Usage](#usage)
7. [Contributors](#contributors)
8. [License](#license)

## Overview

Climate change is one of the most pressing issues of our time, and spreading awareness is critical in addressing its challenges. This system facilitates the efficient management of climate-related news articles, empowering users to stay updated and take informed actions. It provides functionalities for storing, sorting, and analyzing climate change news articles.

## Features

### Key Functionalities
1. **Add Articles**: Input news articles with titles, content, and publication dates.
2. **Search Articles**: Search articles by keywords and view summaries or full content.
3. **Sort Articles**: Organize articles by date in descending order.
4. **Manage Articles**: Delete articles by title and manage stored data with JSON-based persistence.
5. **Graphical User Interface**: Intuitive GUI with tabs for adding, searching, and managing articles.

### Objectives
- **Raise Awareness**: Make climate change information easily accessible and organized.
- **Support Research**: Enable researchers and educators to analyze and share climate-related news.
- **Promote Action**: Empower users with well-structured and accessible data.

## Technical Details

### Core Classes
1. **NewsArticle**: Represents individual articles with attributes like title, content, and date.
2. **JsonHandler**: Manages JSON file operations for data persistence.
3. **ClimateNews**: Handles article management, including adding, searching, sorting, and JSON interactions.
4. **NewsGui**: Implements the Swing-based graphical user interface.
5. **Main**: Serves as the entry point for the application.

### Data Structures
- **ArrayList**: Stores articles dynamically.
- **HashMap**: Handles JSON data as key-value pairs.
- **StringBuilder**: Efficient string manipulation for JSON operations.

## System Requirements

- **Platform**: Any system with Java installed.
- **Languages**: Java
- **Libraries**: Swing for GUI, Java JSON libraries for data handling.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/gilberttetteh/climate-news-management.git
   ```
2. Compile the source code using your preferred Java IDE or the command line:
   ```bash
   javac Main.java
   ```
3. Run the application:
   ```bash
   java Main
   ```

## Usage

1. **Add Articles**: Navigate to the "Add Article" tab, fill in the fields, and click "Add."
2. **Search Articles**: Use the "Search" tab to search for articles by keywords.
3. **Sort Articles**: Organize articles by date using the "Sort by Date" button.
4. **Delete Articles**: Remove articles by title using the "Delete Article" button.
5. **Display Summaries**: View summaries of all stored articles.

## Contributors

This project was collaboratively developed by:
- [Andre Ayiku](https://github.com/AndreAyiku)
- [Sedem Abla](https://github.com/sedemabla)
- [Gilbert Andrew Tetteh](https://github.com/gilberttetteh)
- [Kobinah Kyereboah Coleman](https://github.com/DarthCole)

Special thanks to the team for their contributions to the design, implementation, and documentation of this system.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

### Note
The **Climate News Management System** is designed as an academic project to increase awareness of climate change. Future updates may include additional features like advanced analytics and cloud-based storage options.
```
