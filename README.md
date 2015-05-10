# SoftVis3D - Software visualization in 3D

Visualize your project in SonarQube. Please take a look at http://softvis3d.com for further details, screenshots and live demo.

## Benefits

- Two different views: city and dependency
- Any metric from SonarSource can be used
- Easily navigate through the 3D visualisation
- WebGL technology to support all browsers
- Helps you analyze your software quality easily

## Code city

The "code city" view provides a visualization for the hierarchical structure of the project. Folders or packages are shown as districts, files as buildings. The building footprint and height are dependent on two arbitrary sonar metrics.

## Dependency view

This view is focused on the dependencies within the given structure. The basic 3D layout for the structure is build downwards. The dependencies have been aggregated and can be explored easily without overloading the visualisation.

## Requirenments

SonarQube server with version >= 4.2 and < 5.1.

Due to a bug in SonarQube 4.5 (see SONAR-5699) the plugin does not work in this version. The bug was fixed in 4.5.1 but unfortunately its not possible to get the plugin to work with version 4.5

SoftVis3DPlugin requires the graphviz software to be installed. It should be easily available for your linux or windows server instance. Please take a look at graphviz install page (http://www.graphviz.org/Download.php) for details.


## Install

1. Install Graphiz (http://www.graphviz.org/Download.php)
2. Download plugin jar file (http://softvis3d.com/#/download)
3. Copy to [your SonarQube base path]/extensions/downloads
4. Restart your sonar server
5. Go to SonarQube "Settings" -> "System" -> "General settings" -> "Softviz3d" and define the path to your graphviz installation.
6. On any project in your SonarQube instance, click on "SoftVis3D Viewer" in the navigation section on the left.

Explore your project in 3D city and dependency view!