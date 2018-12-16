# test-DATAJPA-1297
Test case demonstrating the issue DATAJPA-1297 (https://jira.spring.io/browse/DATAJPA-1297)

To reproduce the issue just execute: 
```
mvn test
```

To execute the test with the fix in https://github.com/spring-projects/spring-data-jpa/pull/260 execute: 
```
mvn test -Pfixed
```

