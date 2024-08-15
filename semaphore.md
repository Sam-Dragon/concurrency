# Semaphore

- It allows the number of threads [permits] to execute at a give point of time
  to access shared resource using counters
- If count is more than zero, semaphore will grant threads else it won't
- Semaphore controls the number of threads based on count or permit provided

Example:

- ATM machine
  > Generally, based on number of machines, number of users will be allowed. It can be 'sequential' or 'random' way