# Walkthrough - CRUD Fixes

I have fixed the CRUD functionality and added the necessary activities and layouts. I also resolved ID mismatches in the main activity and fixed a crash in ViewData.

## Changes

### Layouts
#### [NEW] [activity_edit_data.xml](main/res/layout/activity_edit_data.xml)
- Created a form for editing data with fields for Nama, Merk, and Asal.

#### [NEW] [activity_view_single.xml](main/res/layout/activity_view_single.xml)
- Created a detail view to show specific item information.

#### [MODIFY] [activity_main.xml](main/res/layout/activity_main.xml)
- Removed unsafe `android:onClick` attributes to prevent crash when in the runtime.

#### [MODIFY] [activity_view_data.xml](main/res/layout/activity_view_data.xml)
- Replaced incorrect content with a proper `ListView` using `@android:id/list` and an empty view `@android:id/empty`. This fixes the crash in view data.

### Java Classes
#### [MODIFY] [MainActivity.java](MainActivity.java)
- Updated `findViewById` calling in to match the button ID of the layout (`btn_tambah` and `btn_view`).

#### [MODIFY] [EditData.java](EditData.java)
- Fixed incorrect getter method calls (`getNama_barang` -> `getNamaBarang`, etc.).
- Updated to use the correct `DbDataSource.updateBarang` method.

#### [MODIFY] [ViewSingleData.java](ViewSingleData.java)
- Fixed incorrect getter method calls.

#### [MODIFY] [DbDataSource.java](DbDataSource.java)
- Added an overloaded `updateBarang` method to simplify the call from `EditData.java`.

### Conf
#### [MODIFY] [AndroidManifest.xml](AndroidManifest.xml)
- Registered `CreateData`, `EditData`, `ViewSingleData` activities.
- Corrected `ViewData` activity name case.

