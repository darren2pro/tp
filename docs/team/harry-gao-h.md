---
layout: page
title: Gao Haochun's Project Portfolio Page
---

## Project: MrTechRecruiter

#### Overview
MrTechRecruiter (MTR) is a desktop applicant management application designed for tech companies to keep track of job applicants.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

#### **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Harry-Gao-H&tabRepo=AY2122S1-CS2103-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### **New Feature**
* Added the ability to undo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. 
  * Sample pull request: [\#102](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/102)
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future and requires an analysis of design alternatives. The implementation was also challenging as it required changes to existing commands.
  * Credits: The design of undo feature is adapted from [here](https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java.).
  
#### **Enhancements to existing features**
* Implemented command `add-position`, `edit-position`, `edit-applicant`, `delete-position` adapted from AB3
  * Relevant pull requests: [\#42](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/42/files),
  [\#45](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/45),
  [\#86](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/86),
  [\#91](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/91),
  * What is does: the features allow user to add, edit and delete positions, and edit applicants.
  * Justification: the features are essential to MrTechRecruiter because they enable the basic CRUD.
  * Highlights: the features need to deal with the interaction between `position` and `applicant` properly, which is time-consuming to design. Moreover, the implementation should ensure the position that an applicant is applying to should always exist in MrTechRecruiter beforehand. 
  
* Removed AB3-related files from the code base
  * Sample relevant pull request: [\#173](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/173)
  * What is does: Removes all AB3-related classes and tests and disables the AB3-related functionality such as `add` and `edit`.
  * Justification: Our project is adapted from AB3 but AB3-related codes were not removed from our code base until the mock PE. The in-depth analysis of the design structure was needed to perform the removal.
  

* Wrote tests for existing features to increase coverage 
  * Relevant pull requests [\#68](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/68), 
  [\#98](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/98),
  [\#195](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/195)


#### **Documentation**
* User Guide:
    * Added documentation for the features `add-position` and `delete-position`: [\#16](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/16)
    * Did cosmetic tweaks to existing documentation of features `find-applicant`, `add-applicant` and created navigation table: [\#196](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/196/files)
* Developer Guide:
    * Added use cases details of the `add-position` and `delete-position` feature: [\#17](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/17)
    * Did overall check for existing contents: [\#199](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/199)
    * Added the instructions for manual testing for the features `add-position`, `edit-position`, etc.: [\#201](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/201)
    * Updated and improved the implementation section, and drew sequence and activity diagrams: [\#205](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/205)

#### **Contributions to team-based tasks**
* Wrote tests for features written by me and teammates: [\#195](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/195)
* Removed dependency on AB3: [\#173](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/173)

#### **Community**
* PRs reviewed (with non-trivial review comments): [\#77](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/77), [\#24](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/24), [\#81](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/81)
* Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S1/forum/issues/25), [2](https://github.com/nus-cs2103-AY2122S1/forum/issues/49))
* Reported bugs and suggestions for other teams in the class in the mock PE.
