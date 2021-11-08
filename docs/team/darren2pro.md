---
layout: page
title: Darren Heng's Project Portfolio Page
---

### Project: MrTechRecruiter

MrTechRecruiter (MTR) is a desktop applicant management application designed for tech companies to keep track of job applicants. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

#### **Features Added**
Given below are my contributions to the project.

* **New Feature**: Added the ability to add and delete applicants.
  * What it does: allows the user to add applicants to, and delete applicants from MTR.
  * Justification: This feature is paramount to the utility of the app because our app ultimately, is for tech companies to manage job applicants.
  * Highlights: This addition forms the basis for all other features to be added. It required an in-depth understanding of the existing code in order to implement this, right from parsing the user command, to the storage into json file. The implementation was challenging as it required effort to understand almost all the different components of the existing code.
  * Credits: I referenced existing code from AB3, more specifically, the code for adding and deleting persons.

* **New Feature**: Added the ability to store a GitHub profile for each applicant in MTR.
  * What it does: when adding applicants, we now have to specify a GitHub profile link as additional information for each applicant.
  * Justification: This feature is important for recruiters because it allows them to get a better idea of the programming capability of the applicants. This greatly increases the feature utility of MTR for recruiters of tech companies (the GitHub profile is especially significant for tech companies).
  * Highlights: Aside from writing the code for this additional logic in MTR, I also added a GitHub profile hyperlink in the GUI so that users can directly click the hyperlink to launch the GitHub profile in their default browser.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=darren2pro&tabRepo=AY2122S1-CS2103-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)



#### **Project management**
  * Managed releases `v1.3` - `v1.3.1` (2 releases) on GitHub


#### **Enhancements to existing features**
  * Added some test code for Applicant class to increase coverage
    * Relevant pull requests [\#57](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/57)
  * Changed the find command from the existing AB3 code to suit our needs of searching for applicants instead
    * Relevant pull requests [\#101](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/101)
  * Modified the UI to display the different attributes of the applicants
    * Relevant pull requests [\#60](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/60), [#66](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/66)



#### **Documentation**
  * User Guide:
    * Added/improved documentation for the features `add-applicant`, `delete-applicant`, `list-applicant` and `find-applicant` [\#14](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/14), [\#116](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/116/files)
  * Developer Guide:
    * Added implementation details of the `add-applicant` feature. [\#97](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/97)
    * Updated all the UML diagrams for the various components to suit MTR [\#202](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/202)
    * Added use cases and manual testing instructions for commands e.g. `delete-applicant` etc. [\#15](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/15/files), [\#193](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/193)



#### **Community and Contributions to team-based tasks**
  * Maintenance of the repo: proper issue handling, milestone tagging, linking PRs to the relevant issues
  * Reported bugs and suggestions for other teams in the class in the mock PE.
  * Discussion about certain implementations (non-trivial): [\#111](https://github.com/AY2122S1-CS2103-F10-1/tp/pull/111)
  
