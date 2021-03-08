# Cron Expression Parser

A command line application which parses a cron string and expands each field to show the times at which it will run.

The cron string uses the standard cron format with five time fields (minute, hour, day of month, month, and day of week)
plus a command.

The following special characters are supported:

```text
* (All)         - Specifies that event should happen for every time unit
- (Range)       – Determines the value range, e.g. 1-10
, (Values)      - Specifies multiple values, e.g. 1,2,3
/ (Increment)   - Specifies the incremental values, e.g. 30/10
```

The output is formatted as a table with the field name taking the first 14 columns and the times as a space-separated
list following it.

For example, the following input argument:

```text
*/15 0 1,15 * 1-5 /usr/bin/find
```

Will output the following to console:

```text
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

### Build and Run Instructions

Built using Java 15 and Gradle.

In order to run, use the following command in the folder directory to build the project:

```shell
./gradlew clean build
```

Run the following command to execute the application, replacing the cron argument as required:

```shell
./gradlew run --args='"*/15 0 1,15 * 1-5 /usr/bin/find"'
```
