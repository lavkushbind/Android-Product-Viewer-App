# Unit Test Cases for HomeViewModel

This document outlines the unit tests written for the `HomeViewModel` to ensure its logic is correct and robust.

### Test Suite: `HomeViewModelTest.kt`

The test suite is located in the `app/src/test/java/com/dark/wfh/` directory. It uses **JUnit** for the testing framework and **MockK** for mocking the `ProductRepository`.

---

### Test Case 1: Successful Data Fetch

-   **Test Name**: `fetchData success should update uiState to Success`
-   **Objective**: To verify that when the repository returns data successfully, the ViewModel's `uiState` transitions from `Loading` to `UiState.Success` and contains the correct data.
-   **Setup**: The `ProductRepository` is mocked to return a successful `Single` with mock data.
-   **Action**: The `HomeViewModel` is initialized, which automatically calls the `fetchData()` method.
-   **Expected Result**: The test asserts that `viewModel.uiState.value` is an instance of `UiState.Success` and that the data it holds is correct.

---

### Test Case 2: Data Fetching Error

-   **Test Name**: `fetchData error should update uiState to Error`
-   **Objective**: To verify that when the repository returns an error, the ViewModel's `uiState` transitions to `UiState.Error` and contains the correct error message.
-   **Setup**: The `ProductRepository` is mocked to return a `Single.error` with a specific error message.
-   **Action**: The `HomeViewModel` is initialized, triggering the `fetchData()` method.
-   **Expected Result**: The test asserts that `viewModel.uiState.value` is an instance of `UiState.Error` and that the error message it holds is correct.
