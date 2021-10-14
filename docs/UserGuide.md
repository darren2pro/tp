---
layout: page
title: User Guide
---

MrTechRecruiter (MTR) is a **desktop app for managing job applications, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MTR can get your application management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start (coming soon)

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `mrtechrecruiter.jar` from [here](https://github.com/AY2122S1-CS2103-F10-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for MrTechRecruiter.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all applicants.

   * **`add-applicant`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 pos/software engineer` : Adds an applicant named `John Doe` to the `software engineer` position.

   * **`delete-applicant`**`n/John Doe` : Deletes `John Doe` from the applicant list.

   * **`clear`** : Deletes all applicants.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` `[coming soon]`

Shows a message explaining how to access the help page.

Format: `help`

### Adding a position: `add-position`

Adds a position to MrTechRecruiter.

Format: `add-position tit/TITLE des/DESCRIPTION`

Examples:
* `add-position tit/software engineer des/work in a team that builds a facial recognition application`


### Deleting a position: `delete-position`

Deletes the specified position from MrTechRecruiter.

Format: `delete-position INDEX`

* Deletes the position with the specified `INDEX`.

Examples:
* `delete-position 1` deletes the position with index 1.


### Adding an applicant: `add-applicant`

Adds an applicant to MrTechRecruiter.

Format: `add-applicant n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pos/POSITION​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An applicant should only have 1 position. <br>
If the job position exists, its rejection rate should update accordingly.
</div>

Examples:
* `add-applicant n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 pos/software engineer`
* `add-applicant n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 pos/database administrator`

### Deleting an applicant: `delete-applicant`

Deletes the specified applicant from MrTechRecruiter.

Format: `delete-applicant INDEX`

* Deletes the applicant with the specified `INDEX`.

Examples:
* `delete-applicant 1` deletes the applicant with index 1.


### Listing all positions : `list position` [coming soon]

Shows a list of all positions in MrTechRecruiter.

Format: `list position`

### Listing all applicants : `list applicant` [coming soon]

Shows a list of all applicants in MrTechRecruiter.

Format: `list applicant`

### Searching for applicants using keywords: `find` [coming soon]

Finds all applicants whose categorical information match the specified search terms.

Format: `find [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only applicants matching ALL specified search terms specified will be returned.

Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/Mary p/12345678` returns applicants whose names contain `mary` AND whose numbers contain `12345678`

### Clearing all entries : `clear` `[coming soon]`

Clears all entries from MrTechRecruiter.

Format: `clear`

### Exiting the program : `exit` `[coming soon]`

Exits the program.

Format: `exit`

### Saving the data

All data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Data is saved as a JSON file `[JAR file location]/data/POSITION-TITLE.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MrTechRecruiter will discard all data and start with an empty data file at the next run.
</div>

### Editing a person : `edit` `[coming soon]`

Edits an existing applicant in MrTechRecruiter.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MrTechRecruiter home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Applicant** | `add-applicant n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS pos/POSITION` <br> e.g., `add-applicant n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 pos/software engineer`
**Delete Applicant** | `delete-applicant n/NAME`<br> e.g., `delete-applicant John Doe`
**Clear [coming soon]** | `clear`
**Edit [coming soon]** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List [coming soon]** | `list`
**Help [coming soon]** | `help`
