/**
 * This package contains the data models and related exceptions used
 * in the Students Diary application. It includes classes for students,
 * grades, and custom exceptions for managing application-specific errors.
 *
 * Key classes:
 * - {@link Model.Student} -
 * Represents a student in the application.
 * - {@link Model.Grade} -
 * Represents a grade assigned to a student.
 * - {@link Model.StudentList} -
 * Manages the collection of students.
 * - {@link Model.MessagePrinter} -
 * Utility class for printing messages to the console.
 *
 * Key exceptions:
 * - {@link Model.StudentAlreadyExistsException} -
 * Thrown when attempting to add a student that already exists.
 * - {@link Model.StudentNotFoundException} -
 * Thrown when a student cannot be found.
 * - {@link Model.InvalidGradeFormatException} -
 * Thrown when a grade has an invalid format.
 * - {@link Model.InvalidGradeIndexException} -
 * Thrown when an invalid index is used for a grade operation.
 */
package Model;
