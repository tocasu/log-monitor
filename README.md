# Job log monitor application

This application convert log file in CSV format (comma separated values) and identify jobs that has been overdue

## Getting Started

In order to use this application you have to provide log file and place it in "src\main\resources\log\" location.

### Prerequisites

Log file should contains entries like in the example below:

```
11:41:11,background job ulp, START,60134
11:41:28,scheduled task 188, END,23118
11:41:55,background job ulp, END,60134
11:42:46,scheduled task 996, END,90962
11:43:32,background job dej, END,90812
11:44:25,scheduled task 268, START,87228
11:44:43,scheduled task 182, START,70808
```

### Application will analyze data and will generate in the console messages like in the example below:


```
Warning: job with id 87228 took not less than 5 minutes, exactly 9 minutes
Error: job with id 70808 took not less than 10 minutes, exactly 33 minutes
Warning: job with id 71766 took not less than 5 minutes, exactly 5 minutes
Warning: job with id 50295 took not less than 5 minutes, exactly 6 minutes
Warning: job with id 27222 took not less than 5 minutes, exactly 6 minutes
Error: job with id 39860 took not less than 10 minutes, exactly 19 minutes
Warning: job with id 87570 took not less than 5 minutes, exactly 7 minutes
Error: job with id 85742 took not less than 10 minutes, exactly 12 minutes
Error: job with id 22003 took not less than 10 minutes, exactly 11 minutes
Warning: job with id 99672 took not less than 5 minutes, exactly 5 minutes
```

## Authors

* **Gheorghe Marina** - *GitHub repository* - [log-monitor](https://github.com/tocasu/log-monitor)

Functional specifications by [LSEG](https://www.lseg.com/en)

## License

This project is licensed under the MIT License - the code is public and can be used without restrictions.


