---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}
  
--------------------------------------------------------------------------------------------------------------------
## **Introduction**
MrTechRecruiter (MTR) is a standalone desktop app aimed in helping technology-related company recruiters overlook and administer job positions and applicants applying for various jobs in their companies.

With the advent of technology and related jobs, MTR uses Command-Line Interface (CLI) for quicker and easier typing for regular users while maintaining a exemplary Graphical User Interface (GUI).

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**
* Adapted ideas from: [`AddressBook Level-3 (AB-3)`](https://se-education.org/addressbook-level3/)
* Logic Design for `Undo` from: [`Stack Overflow`](https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java)  
* Documentation/Coding standard: [`SE Student Projects`](https://se-education.org/guides/conventions/java/intermediate.html)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**


**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)  [** the link here should be updated ?**]
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component [need changes]

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `MrTechRecruiterParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddPositionCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a position).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.
1. While a modification command is happening, a record of the command's message and the original state of the model before the command occurred is recorded in the separate `Memento` class.
1. The abstract class `Command` is then called to return the `Memento` instance (via `Command#getMomento`) of that state of the model and restores the original state of the model.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete-position 1")` API call.

![Interactions Inside the Logic Component for the `delete-position 1` Command](images/DeleteSequenceDiagram.png)
[this sequence diagram needs update to include the model.record() method]


<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `MrTechRecruiterParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPositionCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPositionCommandParser`, `DeleteApplicantCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the position book and applicant book data i.e., all `Applicant` and `Position` objects (which are contained in a `UniquePositionList` and `UniqueApplicantList` object).
* stores the currently 'selected' `Position` and `Applicant` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Applicant>` or `ObservableList<Position>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)



### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both applicant book data, position book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `ApplicantBookStorage`, `PositionBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

<div markdown="block" class="alert alert-info">

**:information_source: Note:** <br>

* All commands that modify the `ApplicantBook` or `PositionBook` will keep track of the state of the model before the modification using `Memento` class. 
* The `Memento` class captures the existing model and success message from a command and stands by in the event of an `undo` scenario.
* All such commands will have a :heavy_check_mark: symbol beside it. Others will have no symbol displayed beside it.

* Such commands include add-applicant, add-position, delete-applicant & delete-position.

</div>

### Add applicant feature :heavy_check_mark:

The implementation of the add applicant feature is achieved by the `AddApplicantCommand` class. Just like all other
commands in MTR, it extends the Command class. The most important attribute that it has, is the `ApplicantParticulars`
attribute, which contains all the details of the applicant (Name, Phone, Email, Address,
Title of Position Applying to, GithubLink), parsed straight from the user input.

The `AddApplicantCommand#execute(Model model)` method will use guard clauses to check whether there is a duplicate
applicant, and whether the position (that this applicant is applying to) input by the user actually exists in
`positionBook`. If all parameters are valid, the `ApplicantParticulars` will then be passed to Model to add to
`applicantBook`, using the `Model#addApplicantWithParticulars` method.

Given below is an example usage scenario and how the add applicant feature behaves at each step.
Preconditions: The app is already launched and the appropriate position that the new applicant is applying to already
exist.

Step 1. The user inputs the command `add-applicant n/John Doe p/98765432 e/johnd@example.com a/John street,
block 123, #01-01 pos/software engineer github/https://github.com/johndoe`. The app parser will store all the user-input parameters into an
`applicantParticulars` object, and return the `AddApplicantCommand` instance.

The following sequence diagram shows the method invocation in this step.
![AddApplicantSequenceDiagram1](images/add-applicant/AddApplicantSequenceDiagram1.png)
[** this diagram also lacks the memento part. **]

Step 2. LogicManager will execute this `AddApplicantCommand` instance. This will invoke the
`Model#addApplicantWithParticulars` method.

Step 3. Here, we will retrieve the `position` object from `positionBook` if the position exists, using the `positionTitle` that the user
input as argument, and create a new applicant instance using the `applicantParticulars` and `position` object. Then
we will add it to the `applicantBook`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If any of the guard clauses fail, i.e. the
applicant already exist, or the position does not exist, an appropriate exception will be thrown and the applicant
will not be created.

</div>

The following activity diagram summarizes the actions taken when LogicManager executes the AddApplicantCommand:
![AddApplicantActivityDiagram1](images/add-applicant/AddApplicantActivityDiagram1.png)

#### Design considerations:

**Aspect: How and when the new applicant instance is created:**

* **Alternative 1 (current choice):** Saves all the user input as an applicantParticulars object.
    * Pros: Avoids the unnecessary clutter of passing multiple parameters to multiple method calls.
    * Cons: May have lead to greater coupling among classes.

* **Alternative 2:** Each user input parameter (e.g. Name, Address, PositionTitle etc.) are passed to multiple method
  calls.
    * Pros: Will reduce the usage of a new class, thereby reducing coupling.
    * Cons: This could lead to longer method signatures, longer code, and possibly a less OOP approach.
    
### Delete applicant feature :heavy_check_mark:

#### Implementation

The delete-applicant feature is achieved by the `DeleteApplicantCommand` class. Just like all other
commands in MTR, it extends the Command class. The only parameter it takes in is the index position of the applicant in the `ApplicantBook`.

The `DeleteApplicantCommand#execute(Model model)` method will use the `Model#getFilteredApplicantList()` to indirectly
check whether the applicant exists by checking the size of the list against the index provided. The applicant to be deleted is then
obtained from the list via the standard `List#get()` and is removed from the model via `Model#deleteApplicant()`.

Given below is an example usage scenario and how the delete applicant feature behaves at each step.
Preconditions: The app is already launched and there is an applicant existing in MTR.

Step 1: The user inputs the command `delete-applicant 1`. The app parser simply parses the index 1 and returns the `DeleteApplicantCommand` instance.

Step 2: LogicManager executes this `DeleteApplicantCommand` instance, invoking the `Model#deleteApplicant()` method.

Step 3: This then calls the internal method for `ApplicantBook`, `ApplicantBook#removeApplicant()`, which then removes the applicant thereafter.

The following activity diagram summarizes the actions taken when LogicManager executes the DeleteApplicantCommand:
[to be added]

#### Design considerations:

**Aspect: How to access and delete an applicant**

* **Alternative 1 (current choice):** Let ModelManager handle the deletion but keep the ApplicantBook's methods separate from the ModelManager [to be added]
    * Pros: More accessible since ModelManager already contains the applicantBook, and reduces complication of code.
    * Cons: Higher coupling for ModelManager since it handles more commands.

* **Alternative 2:** [to be added]

### Edit applicant feature :heavy_check_mark:

#### Implementation
The implementation of the edit applicant feature uses the `EditApplicantCommand` class. A unique attribute for this 
class is that the user can simply edit any number of fields or attributes to a particular applicant, with at least 1 field being changed.

The `EditApplicantCommand` method takes in an index and description of the target applicant with the help of the `EditApplicantDescriptor` class.
It then checks if the input index is valid by comparing it to the size of the current applicant list in MTR, as well as ensuring it is a non-negative integer.
It also has guard clauses verifying that the description has a valid `Title` which is a valid position title in the current `positionBook`. A final check is done to check that the applicant
with the new description is not already existing in MTR. Once these criteria are met, the model then updates the target applicant with the new description via the
`Model#setApplicant` and `Model#updateFilteredApplicantList` methods. <br>

Given below is an example usage scenario and how the edit applicant feature behaves at each step. <br>
Preconditions: The app is already launched, the target applicant exists.

Step 1. User inputs command `edit-applicant 1 n/Jasmine Doe p/98761432 e/jdoe@example.com`.  The app parser will store 
all the user-input parameters into an `EditApplicantDescriptor` object.

Step 2. LogicManager executes this `EditApplicantCommand` instance, invoking the `EditApplicantDescriptor#createEditedApplicant` method to create a new `Applicant` to replace the original one.

Step 3. The model then replace the existing applicant with the new one in the `applicantBook` via `Model#setApplicant` and reflect the updated list in the UI.

The following activity diagram summarizes the actions taken when LogicManager executes the EditApplicantCommand:
[to be added]

#### Design considerations:

**Aspect: How to access and change an applicant**

* **Alternative 1 (current choice):** Have a separate class handle changing details of an applicant. [to be added]
    * Pros: Lowers coupling and makes logic of ModelManager simpler.
    * Cons: If a change is needed for the EditApplicantCommand, more classes need to be changed, making it more troublesome.

* **Alternative 2:** Create a separate ModelManager to handle applicant-related commands.
    * Pros: Better dissection of code and easier to read and test later on since it is separate from the ModelManager.
    * Cons: May result in a lot more code and work in order to achieve the same level of logic.
    

### Filter applicants feature

#### Implementation

The filter feature is achieved using the functionality of the `FilteredList` class built into JavaFX,
which filters its contents based on a specified `Predicate`.  
This `Predicate` is constructed from the filters specified whenever the `filter-applicant` command is called. 

The `FilterApplicantCommand#execute()` method has guard clauses to check that the contents of the input are valid through the
`FilterApplicantDescriptor#hasAnyFilter()` method. If contents are valid, it uses mapping via the `FilterApplicantCommand#applicantMatchesFilter`
method to filter out all applicants matching the given criteria. A new filtered list is now displayed on the MTR UI. <br>

<div markdown="block" class="alert alert-info"> 
* This command is used for filtering applicants by `Position` and `applicationStatus` only, not to be confused with `FindApplicantCommand`.

</div>

Given below is an example usage scenario of the applicant filter feature. <br>
Preconditions: Applicant exists in MTR and valid filters provided. 

Step 1. User inputs command `filter-applicant status/rejected`. The app parser stores all information in a new `FilterApplicantDescriptor` instance.

Step 2. Model executes `FilterApplicantCommand#applicantMatchesFilter` method my mapping all applicants to check if they meet the criteria/information given.

Step 3. Results of this new filtered list is then passed to the model and is reflected onto the UI.

The following activity diagram summarizes the actions taken when LogicManager executes the FilterApplicantCommand:
[to be added]


#### Rationale for implementation

The `Descriptor` pattern (used similarly in features such as the editing of applicants) comes in handy whenever its corresponding command accepts a variable number of arguments & unspecified arguments are assumed to be ignored.
For instance, the edit applicant feature accepts a variable number of fields to be edited, and leaves all unspecified fields untouched.

The filter feature fits in this category, as the user should be able to specify a variable number of filtering criteria,
and unspecified criteria should be left out of the filter.
Hence, the pattern is implemented here in `FilterApplicantDescriptor`, which is used to construct the `Predicate`.
It is also used to in the validation of the filtering criteria.


#### Design considerations:

**Aspect: Accessing a list**

* **Alternative 1 (current choice):** Use of the Java Streams API to filter the applicants using chained calls to `Stream#filter`.
    * Pros: [to be added]
    * Cons: Does not make good use of the in-built functionality of `FilteredList`.

* **Alternative 2:** [to be added]

**Aspect: Filtering the inputs**

* **Alternative 1(current choice):** Separate class to handle parsing of filtering inputs.
    * Pros: Allows class with methods catered better to our needs (e.g. use of Optional so that fields not filtered by are untouched)
    * Cons: More time-consuming to create from scratch and creation of more test cases.

* **Alternative 2:** Modifying/improving original AB3 `FindCommand` and `FindCommandParser`.
    * Pros: Base code already exists and modifying it would take less time. Test cases also require little modification.
    * Cons: Requires understanding base of the code and high coupling exists.



### Find applicants feature

#### Implementation

The find feature is achieved using the functionality of the `FilteredList` class built into JavaFX,
which finds all applicants based on a specified name. This name is constructed via the `NameContainsKeywordsPredicate` class whenever the `find-applicant` command is called.

The `FindApplicantCommand#execute()` method does not have guard clauses to check that the given name is valid. It simply maps via the `FindApplicantCommand#applicantMatchesFilter`
method to find all applicants matching the given name. A new filtered list is now displayed on the MTR UI. Hence an empty list may be displayed on the UI. <br>

<div markdown="block" class="alert alert-info"> 
* This command is used for finding applicants by their `Name` only, not to be confused with `FilterApplicantCommand`.

</div>

Given below is an example usage scenario of the find applicant feature. <br>
Preconditions: Applicant exists in MTR and valid filters provided.

Step 1. User inputs command `find-applicant John`. The app parser stores all information in a new `NameContainsKeywordsPredicate` instance.

Step 2. Model executes `FindApplicantCommand#execute` method, invoking the `Model#updateFilteredApplicantList` method, mapping all applicants to check if they have the same name.

Step 3. Results of this new filtered list is then passed to the model and is reflected onto the UI.

The following activity diagram summarizes the actions taken when LogicManager executes the FindApplicantCommand:
[to be added]


#### Design considerations:

**Aspect: Finding via name**

* **Alternative 1 (current choice):** Separating searching of name from positions and statuses.
    * Pros: Because of the way we implemented, it is much easier to search via name rather than positions or statuses. Also reduces coupling on the `FilterApplicantCommand` class and easier to test.
    * Cons: Might result in slight confusion for users due to 2 similarly-named commands.

* **Alternative 2:** Use the existing `FindCommand` or created `FilterApplicantCommand` and improve the command from there to achieve this functionality.
    * Pros: A singular class to handle all finding/filtering-related commands, making it easier for users.
    * Cons: Very difficult to code since it requires integrating of multiple existing classes, resulting in potentially many bugs and complicated logic.


### Mark/update applicant's status feature :heavy_check_mark:

#### Implementation
The mark feature is achieved using the `MarkApplicantStatusCommand` class. It is a simple command which only modifies the 
application status of the applicant for a particular position. It does so by taking in the applicant to be modified and the updated `ApplicationStatus`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** There are currently only 3 states for applicants: `Accepted`, `Rejected` and `Pending`.
</div>

The `MarkApplicantStatusCommand#execute` first confirms the existence of the target applicant to be marked using guard clauses.
If the applicant exists, the applicant is updated with the new application status and the model replaces this applicant.

Given below is an example usage scenario of the mark applicant feature. <br>
Preconditions: Applicant exists in MTR and valid mark status given.

Step 1. User inputs `mark john doe status/rejected`. The app parser stores the target applicant name and new `ApplicationStatus` internally in the `MarkApplicantStatusCommand` as private fields.

Step 2. LogicManager executes this `MarkApplicantStatusCommand` instance, invoking the `Applicant#markAs` method and `Model#setApplicant` method, which creates a new applicant and replaces the existing applicant with the created one.

Step 3. UI-wise, the applicant should now appear with the updated application status.

The following activity diagram summarizes the actions taken when LogicManager executes the MarkApplicantStatusCommand:
[to be added]

#### Design considerations:

**Aspect: Accessing the applicant's application status**

* **Alternative 1 (current choice):** Have application status as an enumeration under the `Application` class which serves as an association class between `Applicant` and `Position`.
    * Pros: Simplifies code base since it is accessible via the `Application` class directly.
    * Cons: Higher coupling for `Application` class.

* **Alternative 2:** Have application status in a separate class with enumerations inside it.
    * Pros: Separates code logic from Application, easier to digest and manipulate.
    * Cons: Increases complexity of code. Separate class has little usage.


### List applicants feature

#### Implementation
The list applicants feature is achieved by the `ListApplicantCommand` class. Unlike most other commands in the MTR, 
it only has 1 action under the `ListApplicantCommand#execute` method besides creation of the command itself, which is 
`Model#updateFilteredApplicantList` which updates the UI to show all current applicants in the `applicantBook`.

If there are no current applicants in the `applicantBook`, the UI should appear empty.

Given below is an example usage scenario of the mark applicant feature. <br>
Preconditions: MTR has started up and is working.

Step 1. User inputs `list-applicant`.

Step 2. LogicManager executes this `ListApplicantCommand` instance, invoking the `Model#updateFilteredApplicantList`.

Step 3. The UI is updated to show the current list of applicants.
[display picture of current applicants here]

The following activity diagram summarizes the actions taken when LogicManager executes the ListApplicantCommand:
[to be added]

#### Design considerations:

**Aspect: Listing applicants**

* **Alternative 1 (current choice):** Create a separate command from the original AB3, but follow a similar style.
    * Pros: Better understanding of underlying code and how everything comes together.
    * Cons: More time-consuming.

* **Alternative 2:** Use existing `list` command in AB3 and adapt for MTR.
    * Pros: Many functions already in place and little modification is required.
    * Cons: Although less code to be added, due to coupling, more things are needed to be changed intricately and carefully (i.e. prone to errors/bugs).


### Add position feature :heavy_check_mark:

#### Implementation
The implementation of the add position feature is achieved by the `AddPositionCommand` class. There are 2 things parsed 
straight from the user input: The position `title` and the `description`. A slight difference from the `AddApplicantCommand` 
is that no separate class is used here - the `Position` class simply creates a new instance directly.

The `AddPositionCommand#execute(Model model)` method will use guard clauses to check whether there is a duplicate
position in `positionBook`. If valid, the new position will then be passed to Model to add to
`positionBook`, using the `Model#addPosition` method.

Given below is an example usage scenario and how the add position feature behaves at each step.
Preconditions: The app is already launched and the position to be added is new to MTR.

Step 1. The user inputs the command `add-position tit/software engineer des/work in a team that builds a facial recognition application`. 
The app parser will store all the user-input parameters into a new `Position` instance.

Step 2. LogicManager executes this `AddPositionCommand` instance, invoking the `Model#addPosition` method.

Step 3. The UI for `positionBook` will now contain the new position added.

The following sequence diagram shows the method invocation in this step.
[to be added]

#### Design considerations:

**Aspect: Adding positions**

* **Alternative 1 (current choice):** Have a singular `Position` class to handle all position related methods.
    * Pros: Simplifies coding and testing since everything is parked under 1 class.
    * Cons: Higher coupling for the `Position` class.

* **Alternative 2:** Have a separate class which contains details of various positions like applicants.
    * Pros: Separates details from the actual class.
    * Cons: Unnecessary since positions have few fields and functions, additional class simply increases complexity. Defeats the purpose of the `Position` class.


### Delete position feature :heavy_check_mark:

#### Implementation

The delete-position feature is achieved by the `DeletePositionCommand` class, in similar flavour to the `DeleteApplicantCommand`. 
The only parameter it takes in is the index position of the position in the `positionBook`.

The `DeletePositionCommand#execute(Model model)` method will use the `Model#getFilteredPositionList()` to indirectly
check whether the position exists by checking the size of the list against the index provided. The position to be deleted is then
obtained from the list via the standard `List#get()` and is removed from the model via `Model#deletePosition()`.

Given below is an example usage scenario and how the delete position feature behaves at each step.
Preconditions: The app is already launched and there is a position existing in MTR.

Step 1: The user inputs the command `delete-position 1`. The app parser simply parses the index 1 and returns the `DeletePositionCommand` instance.

Step 2: LogicManager executes this `DeletePositionCommand` instance, invoking the `Model#deletePosition()` method.

Step 3: This then calls the internal method for `PositionBook`, `PositionBook#removeApplicant()`, which then removes the position thereafter.

The following activity diagram summarizes the actions taken when LogicManager executes the DeletePositionCommand:
[to be added]

#### Design considerations:

**Aspect: How to access and delete a position**

* **Alternative 1 (current choice):** Let ModelManager handle the deletion but keep the PositionBook's methods separate from the ModelManager
    * Pros: More accessible since ModelManager already contains the positionBook, and reduces complication of code.
    * Cons: Higher coupling for ModelManager since it handles more commands.

* **Alternative 2:** Create a separate ModelManager to handle position-related commands.
    * Pros: Better dissection of code and easier to read and test later on since it is separate from the ModelManager.
    * Cons: May result in a lot more code and work in order to achieve the same level of logic.


### Edit position feature :heavy_check_mark:

#### Implementation
The implementation of the edit position feature uses the `EditPositionCommand` class, in similar flavour to the `EditApplicantCommand`. A unique attribute for this
class is that the user can simply edit any number of fields or attributes to a particular applicant, with at least 1 field being changed.

The `EditPositionCommand` method takes in an index and description (including `title`) of the target position with the help of the `EditPositionDescriptor` class.
It then checks if the input index is valid by comparing it to the size of the current applicant list in MTR, as well as ensuring it is a non-negative integer.
It also has guard clauses verifying that the description is valid and different from the one in the MTR. Once these criteria are met, 
the model then updates the target position with the new description via the `Model#setPosition` and `Model#updateFilteredPositionList` methods.
It also ensures all applicant's positions are updated using the `Model#updateApplicantsWithPosition`.

Given below is an example usage scenario and how the edit position feature behaves at each step. <br>
Preconditions: The app is already launched, the target position exists.

Step 1. User inputs command `edit-position 1 tit/Algorithm Engineer des/embed algorithms into the facial recognition application `.  The app parser will store
all the user-input parameters into an `EditPositionDescriptor` object.

Step 2. LogicManager executes this `EditPositionCommand` instance, invoking the `EditPositionDescriptor#createEditedPosition` method to create a new `Position` to replace the original one.

Step 3. The model then replaces the existing position with the new one in the `positionBook` via `Model#setPosition` and reflect the updated list in the UI.

The following activity diagram summarizes the actions taken when LogicManager executes the EditPositionCommand:
[to be added]

#### Design considerations:

**Aspect: How to access and change a position**

* **Alternative 1 (current choice):** Have a separate class handle changing details of a position. [to be added]
    * Pros: Lowers coupling and makes logic of ModelManager simpler.
    * Cons: If a change is needed for the EditPositionCommand, more classes need to be changed, making it more troublesome.

* **Alternative 2:** Create a separate ModelManager to handle position-related commands.
    * Pros: Better dissection of code and easier to read and test later on since it is separate from the ModelManager.
    * Cons: May result in a lot more code and work in order to achieve the same level of logic.
    

### List positions feature

#### Implementation
The list positions feature is achieved by the `ListPositionCommand` class. Unlike most other commands in the MTR,
it only has 1 action under the `ListPositionCommand#execute` method besides creation of the command itself, which is
`Model#updateFilteredPositionList` which updates the UI to show all current positions in the `positionBook`.

If there are no current positions in the `positionBook`, the UI should appear empty.

Given below is an example usage scenario of the mark applicant feature. <br>
Preconditions: MTR has started up and is working.

Step 1. User inputs `list-position`.

Step 2. LogicManager executes this `ListPositionCommand` instance, invoking the `Model#updateFilteredPositionList`.

Step 3. The UI is updated to show the current list of positions.
[display picture of current positions here]

The following activity diagram summarizes the actions taken when LogicManager executes the ListPositionCommand:
[to be added]

#### Design considerations:

**Aspect: Listing positions**

* **Alternative 1 (current choice):** Create a separate command from the original AB3, but follow a similar style.
    * Pros: Better understanding of underlying code and how everything comes together.
    * Cons: More time-consuming.

* **Alternative 2:** Use existing `list` command in AB3 and adapt for MTR.
    * Pros: Many functions already in place and little modification is required.
    * Cons: Due to coupling, requires changing code intricately and carefully (i.e. prone to errors/bugs).


### Rejection Rate feature

#### Proposed Implementation

The proposed rejection rate mechanism is facilitated by `Model` and `Calculator`.
The `Model` component checks if the position exists and accesses it, while `Calculator` calculates the rejection rate (if applicable).
Implements the following functions:
* `ModelManager#hasPositionWithTitle()`  — Checks if a position with a given title exists in the MTR.
* `Calculator#calculateRejectionRate()`  — Calculates the rejection rate of a position based on the number of total applicants and number of rejected applicants for that position.

These operations are exposed in the `Model` interface as `Model#hasPositionWithTitle()` and `Model#calculateRejectionRate` respectively.

Given below is an example usage scenario and how the rejection rate mechanism works at every step. <br>
Preconditions: Position exists in MTR and there is at least 1 applicant for this position (regardless of status).

Step 1. The user launches the application which is assumed to have some positions and corresponding applicants applying for them in the MTR.

Step 2. The user executes `rate pos/software engineer` command to calculate the rejection rate of Software Engineer in the PositionBook.
The `rate` command calls `Model#hasPositionWithTitle`, causing the model to check whether `Software Engineer` exists in the database as a Position.

Step 3. If the position exists, it will access the ApplicantBook via `Model#calculateRejectionRate()`, beginning a count of the number of applicants for the position as well as the number of rejected applicants of the same position.

Step 4. After these numbers have been obtained, the `Calculator` class is called and calculates via `Calculator#calculateRejectionRate`. This resulting floating point number is then the rejection rate of the position.

The following sequence diagram shows the method invocation in this step.
![SeqDiagram](images/rejection-rates/SeqDiagram.png)

Step 5. Any command the user executes next simply refreshes the current state to its original state as shown in step 1.

#### Design considerations:

#### Aspect: How rejection rate executes:

* **Alternative 1** (current choice): Calculate the rejection rate only when needed. No storing required.
    * Pros: Saves a significant amount of space and reduces immutability. Implementation is simple.
    * Cons: A user could want to calculate many rejection rates frequently and hence not storing these values might have performance issues in the long run.
* **Alternative 2**: Store all rejection rates with their respective positions in a dictionary.
    * Pros: Accessing the rejection rates of a certain position will only require access to the dictionary and nothing else - limited accessibility.
      Also, accessing a rejection rate will be much quicker.
    * Cons: Potentially a large amount of space required, slowing performance. Also, the dictionary needs to be updated everytime an applicant's status changes or when a position/applicant is added/deleted,
      which could result in many inter-linked implementations for the dictionary, rendering it slow. May be difficult to show change in UI as well with many layers affected.

The following activity diagram summarizes the actions taken when LogicManager executes the RejectionRateCommand:
![ActivityDiagram](images/rejection-rates/ActivityDiagram.png)


### Undo feature

#### Implementation

The proposed undo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.



--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

This product is for HR departments of tech companies that have a large number of applicants and complicated recruitment processes.

Additionally, the user:

* has a need to manage a significant number of applicants
* Needs to quickly search for an applicant using its name
* Needs to quickly search for the list of applicants from the position that they are applying to
* Needs to quickly search for the list of applicants with a particular application status
* Want to view the rejection rates of the various positions so that they can gain insights on which positions are most competitive
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

An efficient applicant management system for HR departments of technology companies to track application statuses and store applicant information.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | general user                               | add new job positions | Add applicants to these positions. |
| `* * *`  | general user                               | delete existing job positions | Remove irrelevant, out-of-date jobs. |
| `* * *`  | general user                               | edit existing job positions | update the position name and description according to my company's changes.|
| `* * *`  | general user                               | see the current list of positions | have a quick overview of current positions in the company. |
| `* * *`  | general user                               | add a new applicant under a position      | Store his/her information within the system.    |
| `* * *`  | general user                               | delete an applicant from under a position | Remove applicants that are no longer related to this position.          |
| `* * *`  | general user                               | edit existing applicants | update the applicant's name and relevant information accordingly.|
| `* * *`  | general user                               | find applicants based on their name, position or application status | compare applicants' relevant information. |
| `* * *`  | general user                               | update applicants' application statuses directly | quickly update and see positions' competitiveness. |
| `* * *`  | general user                               | see the current list of applicants | have a quick overview of applicants that have applied to the various positions. |
| `* * *`  | general user                               | view the average rejection rates of all job positions | gauge how competitive a position might be.          |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App.              |
| `* *  `  | user                                       | hide private applicant details   | ensure confidentiality of applicants' information. |
| `* *  `  | user                                       | undo my last command/action | retract mistakes or changes made in the command. |
| `*    `  | user with many applicants in the address book | sort applicants by name           | locate an applicant easily.  |
| `*    `  | user                                       | see a graphical representation of statuses in a position | have a quick visualisation on how competitive a position might be. |


### Use cases
(For all use cases below, the **System** is `MrTechRecruiter` and the **Actor** is the `user`, unless specified otherwise.)

**<u>Use Case: UC1 - Add a new position</u>**

Preconditions: NA

Guarantees: New position is saved with information displayed.

**MSS:**

1. User requests to add a new position, with necessary details.
2. MrTechRecruiter adds the new position and shows a success message. <br>
   Use case ends.

**Extensions:**

* 1a. The format is invalid.
    * 1a1. MrTechRecruiter shows an error message for invalid format. <br>
      Use case ends.
  
* 1b. The position already exists in MTR.
    * 1b1. MrTechRecruiter shows an error message for duplicate position. <br>
      Use case ends.


**<u>Use Case: UC2 - Delete a position</u>** 

Preconditions: The specified position exists.

Guarantees: Position and applicants applying to the position are deleted from MTR and display is updated.

**MSS:**

1. User requests to delete a position via index.
2. MrTechRecruiter deletes the existing position from the position book and shows a success message.
3. All applicants with this existing position will also be consequently deleted. <br>
   Use case ends.

**Extensions:**

* 1a. The format provided is invalid or the index is negative.
    * 1a1. MrTechRecruiter shows an error message for invalid format. <br>
      Use case ends.

* 1b. The index provided is larger than the number of displayed positions.
    * 1b1. MrTechRecruiter displays a message that there are no current positions. <br>
      Use case ends.


**<u>Use Case: UC3 - Edit a position</u>**

Preconditions: The specified position exists.

Guarantees: Position in MTR is changed and display is updated.

**MSS:**

1. User requests to edit a position via index, providing a new title and new description.
2. MrTechRecruiter updates the existing position in the position book and shows a success message. <br>
   Use case ends.

**Extensions:**

* 1a. The index provided is invalid (negative or larger than positionBook size)
    * 1a1. MrTechRecruiter shows an error message for invalid format. <br>
    Use case ends.

* 1b. The position book is empty.
    * 1b1. MrTechRecruiter displays a message that no position is in the list. <br>
    Use case ends.

* 1c. Title provided or description provided is invalid.
    * 1c1. MrTechRecruiter shows an error message for invalid format. <br>
    Use case ends.

* 1d. The position to be changed has the same title and description.
    * 1d1. MrTechRecruiter shows an error message saying that the edited position is the same. <br>
    Use case ends.


**<u>Use Case: UC4 - Add a new applicant </u>**

Preconditions: NA

Guarantees: Applicant is added to MTR and display is updated.

**MSS:**

1.  User requests to add a new applicant and provides all the necessary parameters/details.
2.  MrTechRecruiter adds the new applicant and shows a success message. <br>
    Use case ends.

**Extensions:**

* 1a. The format is invalid.
    * 1a1. MrTechRecruiter shows an error message for invalid format. <br>
      Use case ends.

* 1b. The applicant to be added already exists in MrTechRecruiter.
    * 1b1. MrTechRecruiter shows an error message indicating duplicate applicant. <br>
      Use case ends.

* 1c. The position specified in parameters does not exist in MTR.
    * 1c1. MrTechRecruiter shows an error message indicating that position does not exist. 


**<u>Use Case: UC5 - Delete an applicant</u>**

Preconditions: NA

Guarantees: Applicant is deleted from MTR and display is updated.

**MSS**

1.  User requests to delete an applicant via index.
2.  MrTechRecruiter deletes specified applicant in the list and UI updated to not show the deleted applicant. <br>
    Use case ends.

**Extensions**
* 1a. The index provided is invalid (negative or larger than `applicantBook` size)
  * 1a1. MrTechRecruiter shows an error message for invalid format. <br>
    Use case ends.

* 1b. The `applicantBook` is empty.
  * 1b1. MrTechRecruiter displays a message that there is currently no applicants. <br>
    Use case ends.
    

**<u>Use Case: UC6 - Edit a new applicant</u>** 

Preconditions: NA

Guarantees: Applicant in MTR is edited and display is updated.

**MSS:**

1. User requests to edit an applicant via index, giving the updated details.
2. MrTechRecruiter updates the existing applicant and shows a success message.
3. The UI will also be updated to show the applicant's new details. <br>
   Use case ends.

**Extensions:**
* 1a. The index provided is invalid (negative or larger than applicantBook size)
  * 1a1. MrTechRecruiter shows an error message for invalid format. <br>
    Use case ends.

* 1b. The Applicant book is empty.
  * 1b1. MrTechRecruiter displays a message that there is currently no applicants. <br>
    Use case ends.

* 1c. Any new details provided is invalid.
  * 1c1. MrTechRecruiter shows an error message for invalid format. <br>
    Use case ends.

* 1d. The applicant to be changed has the same description overall.
  * 1d1. MrTechRecruiter shows an error message saying that the edited applicant is the same. <br>
    Use case ends.

* 1e. The position specified in parameters does not exist in MTR.
  * 1e1. MrTechRecruiter shows an error message indicating that position does not exist.


**<u>Use Case: UC7 - Listing current positions</u>**

Preconditions: NA

Guarantees: NA

**MSS:**

1. User requests to view all current positions.
2. MrTechRecruiter refreshes the UI and reflects all existing positions. <br>
   Use case ends.


**<u>Use Case: UC8 - Listing current applicants</u>**

Preconditions: NA

Guarantees: NA

**MSS:**

1. User requests to view all current applicants.
2. MrTechRecruiter refreshes the UI and reflects all existing applicants. <br>
   Use case ends.



**<u>Use Case: UC9 - Finding applicants by name</u>**

Preconditions: NA

Guarantees: NA

**MSS:**

1. User requests to find all applicants containing a specified name.
2. MrTechRecruiter searches through all applicants and returns a list of all applicants containing the name, displaying it in the UI. <br>
   Use case ends.

**Extensions:**
* 1a. Invalid name given by user.
  * 1a1. MrTechRecruiter shows an error message indicating invalid name. <br>
    Use case ends.

* 1b. No applicants contain the specified name.
  * 1b1. MrTechRecruiter shows a message indicating no applicants matched the given search. <br>
    Use case ends.


**<u>Use Case: UC10 - Finding applicants by position</u>**

Preconditions: NA

Guarantees: NA

**MSS:**

1. User requests to find all applicants that applied to a specific position.
2. MrTechRecruiter searches through all applicants and returns a list of all applicants who applied to the position, displaying it in the UI. <br>
   Use case ends.

**Extensions:**
* 1a. Invalid position name given by user.
  * 1a1. MrTechRecruiter shows an error message indicating invalid position name. <br>
    Use case ends.

* 1b. No applicants have applied for that particular position.
  * 1b1. MrTechRecruiter shows a message indicating no applicants have applied for the selected position. <br>
    Use case ends.


**<u>Use Case: UC11 - Finding applicants by application status</u>**

Preconditions: NA

Guarantees: NA

**MSS:**

1. User requests to find all applicants with a specified application status.
2. MrTechRecruiter searches through all applicants and retrieves all applicants with the specified application status, displaying it in the UI. <br>
   Use case ends.

**Extensions:**
* 1a. Invalid application status given by user.
  * 1a1. MrTechRecruiter shows an error message indicating invalid application status. <br>
    Use case ends.

* 1b. No applicants have that particular application status.
  * 1b1. MrTechRecruiter shows a message indicating no applicants have that current application status. <br>
    Use case ends.


**<u>Use Case: UC12 - Update applicant's application status</u>**

Preconditions: NA

Guarantees: If updated, MTR will update and reflect the updated status.

**MSS:**

1. User requests to update an applicant's application status, giving the name and status.
2. MrTechRecruiter replaces the existing applicant and updates the UI. <br>
   Use case ends.

**Extensions:**
* 1a. Invalid application status given by user.
  * 1a1. MrTechRecruiter shows an error message indicating invalid parameters. <br>
    Use case ends.

* 1b. Invalid name provided.
  * 1b1. MrTechRecruiter shows an error message indicating invalid parameters. <br>
    Use case ends.
    
* 1c. No current applicants in the `applicantBook`.
  * 1c1. MrTechRecruiter displays a message indicating no such applicant. <br>
    Use case ends.


**<u>Use case: UC13 - Viewing average rejection rates for a job position.</u>**

Preconditions: NA

Guarantees: NA

**MSS:**

1. User requests to see the rejection rate of a particular position.
2. MrTechRecruiter calculates and returns the rejection rate. <br>
   Use case ends.

**Extensions:**
* 1a. Invalid position name given
    * 1a1. MrTechRecruiter shows an error message indicating invalid position name. <br>
      Use case ends.

* 1b. No current applicants for that position.
    * 1b1. MrTechRecruiter displays a message indicating no current applicants for that position. <br>
      Use case ends.


**<u>Use case: UC14 - Viewing breakdown of statuses of a position.</u>**

Preconditions: NA

Guarantees: NA

**MSS:**

1. User requests to see the breakdown of statuses of a particular position.
2. MrTechRecruiter calculates and displays a pie chart showing distribution of current statuses of applicants for this position. <br>
   Use case ends.

**Extensions:**
* 1a. Invalid position name given
  * 1a1. MrTechRecruiter shows an error message indicating invalid position name. <br>
    Use case ends.

* 1b. No current applicants for that position.
  * 1b1. MrTechRecruiter displays a message indicating no current applicants for that position. <br>
    Use case ends.


**<u>Use case: U15 - Undoing a previous command.</u>**

Preconditions: NA

Guarantees: Given an applicable command previously, MrTechRecruiter will update its state accordingly along with its UI.

**MSS:**

1. User requests to undo a previous command entered.
2. MrTechRecruiter changes the state of `applicantBook` and `positionBook` to before the last applicable command occurred.
3. The old `applicantBook` and `positionBook` is reflected in the UI. <br>
   Use case ends.

**Extensions:**
* 1a. No applicable commands have been entered previously (i.e. no adding, editing or deletion of positions or applicants has occurred).
  * 1a1. MrTechRecruiter displays an error message indicating it cannot `undo` due to no previous record. <br>
    Use case ends.


**<u>Use case: U16 - Requesting the help menu.</u>**

Preconditions: NA

Guarantees: Prompts opening of a window containing the link to our User Guide.

**MSS:**

1. User requests for the help page.
2. MrTechRecruiter automatically opens up a window containing the link to our User Guide.

**Extensions:**
NA


**<u>Use case: U17 - Exiting MrTechRecruiter.</u>**

Preconditions: NA

Guarantees: Saving of any current data in MrTechRecruiter before shutting down.

**MSS:**

1. User requests to exit MrTechRecruiter.
2. MrTechRecruiter does a final save before shutting down.

**Extensions:**
NA



### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A new user should be able to easily navigate through MrTechRecruiter and perform basic tasks (e.g. adding/deleting).
5. Should be portable (i.e. work without requiring an installer).
6. Should not depend on a remote server.
7. Should work for standard (i.e. industry-standard Full HD 1080p resolution) screen resolutions and higher.
8. Should be packaged into a single, compact (~100MB) file.
9. Developer & User guides should be PDF friendly.
10. The app should only read from the specified JSON file for persisted data.



### Glossary

* **Applicant**: A potential hire that is applying for a particular job position.
* **Application Status / Status**: Current application status of an applicant for a particular position. Can be `Accepted`, `Rejected` or `Pending`.
* **CLI**: Command-line interface. CLI programs take in input in the form of text-based commands, usually input by the user, to execute the program's various functions.
* **Guarantees**: Conditions that will be fulfilled after the use case ends.  
* **Job position**: A job opening within the user's company that is looking for potential hires.
* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **MSS**: Main Success Scenario, the usual steps and outcome of an actor and a use case.
* **NA**: Not Applicable.
* **Preconditions**: Conditions that have to be fulfilled before the use case begins.  
* **Private contact detail**: A contact detail not meant to be circulated.
* **Rejection rate**: The percentage of jobs for a particular job position that was not accepted by the employee or employer.
* **Rejection rate calculation** = `No. of rejected applicants for a position` / `Total no. of applicants applying to the position`.
* **UC** = Use Case.

--------------------------------------------------------------------------------------------------------------------


## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.



### Adding a new position

1. Adding a position to MrTechRecruiter
   1. Prerequisites: -
   2. Test case: `add-position tit/tester desc/test codes`<br>
      Expected: The position `tester` is added to MTR. The detailed information is shown in the status message.
   3. Test case (followed by the previous test case): `add-position tit/tester desc/testing`<br>
      Expected: An error message will show, indicating that the position `tester` already exists in MTR.

### Editing a position
1. Editing a position in MrTechRecruiter. 
   1. Prerequisites: There is at least one position in MTR. Assume there are two positions, `software engineer` at index `1` and `tester` at index `2`
   2. Test case: `edit-position 1 tit/data engineer desc/create data pipeline`<br>
      Expected: The title of the position is changed to `data engineer`, and the description is also changed. 
   3. Test case: `edit-position 1 tit/tester`<br>
      Expected: An error message will show, indicating that the position `tester` already exists in MTR.

### Deleting a position

1. Deleting a position from MrTechRecruiter 
   1. Prerequisites: There is at least one position in MTR. Assume there are two positions, `software engineer` at index `1` and `tester` at index `2`
   2. Test case: `delete-position 1`<br>
      Expected: `tester` is deleted from the position list. The detailed information is shown in the status message. 
   3. Test case: `delete-applicant 3`<br>
      Expected: An error message will show, indicating that the index is invalid.

      


### Adding a new applicant

1. Adding an applicant to MrTechRecruiter

    1. Prerequisites: -

    1. Test case: `add-applicant n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 pos/software engineer github/https://github.com/empty`<br>
       Expected: John Doe, with all the relevant details that were passed as parameters is added to MrTechRecruiter.

    1. Test case: `add-applicant n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 pos/software engineer github/https://github.com/`<br>
       Expected: An error message will show, indicating that the github url passed is not a valid gitHub profile url (because it is the gitHub homepage).


### Editing an applicant

1. Editing an applicant in MrTechRecruiter
   1. Prerequisites: There is at least one applicant in MTR. Assume there are two positions `software engineer` and `tester` in MTR and for applicants, at index `1` we have `John Doe`, and at index `2` we have Mary Jane.
   2. Test case: `edit-applicant 1 p/89385853 pos/tester`<br>
      Expected: The phone and the position of `John Doe` are successfully edited. A success message will show. 
   3. Test case: `edit-applicant 2 n/Mary`<br>
      Expected: The name of the applicant at index `2` is successfully edited. A success message will show.
   4. Other incorrect command to try: `edit-applicant 5`
      Expected: An error message will show, indicating that the index is invalid. 


### Deleting an applicant

1. Deleting an applicant from MrTechRecruiter

  1. Prerequisites: There are 2 applicants within MTR. At index `1` we have `John Doe`, and at index `2` we have Mary Jane.

  1. Test case: `delete-applicant 1`<br>
     Expected: John Doe is deleted from the list. Details of the deleted contact shown in the status message.

  1. Test case: `delete-applicant 3`<br>
     Expected: No applicant is deleted. Error details depicting index out of bounds is shown.
  




### Viewing average rate of a job

1. Viewing average rate of a job in MrTechRecruiter

    1. Prerequisites: <br>
       a. Job must exist in address book. <br>
       b. Average rate already tabulated for the job.

    1. Test case: `rate pos/software engineer`<br>
       Expected: Text indicating the rejection rate will be displayed in the status bar. E.g. `Rejection rate for software engineer = 10.00%`


### Undoing 

1. Undoing the previous modification in MrTechRecruiter
   1. Test case: `undo`
      Expected: If modification has been made to MTR, the previous modification will be reverted. 
        Otherwise, an error message will be shown, indicating that there is no modification to undo. 


### Saving data

1. Dealing with missing/corrupted data files

    1. If there is a data/applicantbook.json in the same directory as the jar file, open the file using a text editor and remove a random bracket. Launch the program.
       Expected: A warning of the invalid json file will be shown in the terminal. An empty applicantbook will be launched.

    1. If there is no data/applicantbook.json in the same directory as the jar file, first launch MTR, then type `exit` into the command
       box. A sample json file will be created. Then repeat the steps as above.

    1. The steps for data/positionbook.json is similar.
