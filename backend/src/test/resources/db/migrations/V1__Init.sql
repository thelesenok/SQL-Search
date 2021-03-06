CREATE TABLE `CUSTOMERS` (
  `CUSTONER_ID` int(11) NOT NULL,
  `CUSTOMER_FIRST_NAME` varchar(1024) DEFAULT NULL,
  `CUSTOMER_LAST_NAME` varchar(1024) DEFAULT NULL,
  `CUSTOMER_MIDDLE_NAME` varchar(1024) DEFAULT NULL,
  `ORGANIZATION_ID` int(11) NOT NULL,
  `CUSTOMER_TYPE_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `CUSTOMER_TYPES` (
  `CUSTOMER_TYPE_ID` int(11) NOT NULL,
  `CUSTOMER_TYPE_NAME` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `EXECUTORS` (
  `EXECUTOR_ID` int(11) NOT NULL,
  `EXECUTOR_FIRST_NAME` varchar(1024) DEFAULT NULL,
  `EXECUTOR_LAST_NAME` varchar(1024) DEFAULT NULL,
  `EXECUTOR_MIDDLE_NAME` varchar(1024) DEFAULT NULL,
  `ORGANIZATION_ID` int(11) NOT NULL,
  `EXECUTOR_TYPE_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `EXECUTOR_TYPES` (
  `EXECUTOR_TYPE_ID` int(11) NOT NULL,
  `EXECUTOR_TYPE_NAME` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ORGANIZATIONS` (
  `ORGANIZATION_ID` int(11) NOT NULL,
  `ORGANIZATION_NAME` varchar(1024) NOT NULL,
  `ORGANIZATION_MANAGEMENT_TYPE_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ORGANIZATION_MANAGEMENT_TYPES` (
  `ORGANIZATION_MANAGEMENT_TYPE_ID` int(11) NOT NULL,
  `ORGANIZATION_MANAGEMENT_TYPE_NAME` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECTS` (
  `PROJECT_ID` int(11) NOT NULL,
  `NAME` varchar(1024) DEFAULT NULL,
  `PLAN_DATE_START` datetime DEFAULT NULL,
  `PLAN_DATE_END` datetime DEFAULT NULL,
  `FACT_DATE_END` datetime DEFAULT NULL,
  `FACT_DATE_START` datetime DEFAULT NULL,
  `EXECUTOR_ID` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `PROJECT_METHODOLOGY_ID` int(11) NOT NULL,
  `PROJECT_BUDGET` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_CASES` (
  `PROJECT_CASE_ID` int(11) NOT NULL,
  `PROJECT_CASE_DISCRIPTION` text NOT NULL,
  `PROJECT_CASE_SOLUTION` text NOT NULL,
  `PROJECT_CASE_STAGE_ID` int(11) NOT NULL,
  `PROJECT_CASE_EFFICIENCY` double NOT NULL,
  `PROJECT_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_METHODOLOGIES` (
  `PROJECT_METHODOLOGY_ID` int(11) NOT NULL,
  `PROJECT_METHODOLOGY_NAME` varchar(1024) DEFAULT NULL,
  `PROJECT_METHODOLOGIES_TYPE_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_METHODOLOGIES_TYPES` (
  `PROJECT_METHODOLOGIES_TYPE_ID` int(11) NOT NULL,
  `PROJECT_METHODOLOGIES_TYPE_NAME` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_STAGES` (
  `PROJECT_STAGE_ID` int(11) NOT NULL,
  `PROJECT_STAGE_TYPE_ID` int(11) NOT NULL,
  `EXECUTOR_ID` int(11) NOT NULL,
  `PROJECT_ID` int(11) NOT NULL,
  `PLAN_DATE_START` date DEFAULT NULL,
  `PLAN_DATE_END` date DEFAULT NULL,
  `FACT_DATE_START` date DEFAULT NULL,
  `FACT_DATE_END` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_STAGE_TYPES` (
  `PROJECT_STAGE_TYPE_ID` int(11) NOT NULL,
  `STAGE_NAME` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_TASKS` (
  `PROJECT_TASK_ID` int(11) NOT NULL,
  `PROJECT_TASK_NAME` varchar(1024) NOT NULL,
  `EXECUTOR_ID` int(11) NOT NULL,
  `PLAN_DATE_START` date DEFAULT NULL,
  `PLAN_DATE_END` date DEFAULT NULL,
  `FACT_DATE_START` date DEFAULT NULL,
  `FACT_DATE_END` date DEFAULT NULL,
  `PROJECT_STAGE_ID` int(11) NOT NULL,
  `PROJECT_TASK_TYPE_ID` int(11) NOT NULL,
  `PROJECT_TASK_PRIORITY_ID` int(11) NOT NULL,
  `PROJECT_TASK_STATUS_ID` int(11) NOT NULL,
  `PROJECT_TASK_ESTIMATION` int(11) NOT NULL,
  `PROJECT_TASK_STORY_POINTS` int(11) NOT NULL,
  `PROJECT_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_TASK_PRIORITIES` (
  `PROJECT_TASK_PRIORITY_ID` int(11) NOT NULL,
  `PROJECT_TASK_PRIORITY_NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_TASK_STATUSES` (
  `PROJECT_TASK_STATUS_ID` int(11) NOT NULL,
  `PROJECT_TASK_STATUS_NAME` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_TASK_TYPES` (
  `PROJECT_TASK_TYPE_ID` int(11) NOT NULL,
  `PROJECT_TASK_TYPE_NAME` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_TYPES` (
  `PROJECT_TYPE_ID` int(11) NOT NULL,
  `PROJECT_ID` int(11) NOT NULL,
  `PROJECT_TYPE_NAME_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_TYPE_KINDS` (
  `PROJECT_TYPE_KIND_ID` int(11) NOT NULL,
  `PROJECT_TYPE_KIND_NAME` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PROJECT_TYPE_NAMES` (
  `PROJECT_TYPE_NAME_ID` int(11) NOT NULL,
  `NAME` varchar(1024) DEFAULT NULL,
  `PROJECT_TYPE_KIND_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `CUSTOMERS`
  ADD PRIMARY KEY (`CUSTONER_ID`);

ALTER TABLE `CUSTOMER_TYPES`
  ADD PRIMARY KEY (`CUSTOMER_TYPE_ID`);

ALTER TABLE `EXECUTORS`
  ADD PRIMARY KEY (`EXECUTOR_ID`);

ALTER TABLE `EXECUTOR_TYPES`
  ADD PRIMARY KEY (`EXECUTOR_TYPE_ID`);

ALTER TABLE `ORGANIZATIONS`
  ADD PRIMARY KEY (`ORGANIZATION_ID`);

ALTER TABLE `ORGANIZATION_MANAGEMENT_TYPES`
  ADD PRIMARY KEY (`ORGANIZATION_MANAGEMENT_TYPE_ID`);

ALTER TABLE `PROJECTS`
  ADD PRIMARY KEY (`PROJECT_ID`);

ALTER TABLE `PROJECT_CASES`
  ADD PRIMARY KEY (`PROJECT_CASE_ID`);

ALTER TABLE `PROJECT_METHODOLOGIES`
  ADD PRIMARY KEY (`PROJECT_METHODOLOGY_ID`);

ALTER TABLE `PROJECT_METHODOLOGIES_TYPES`
  ADD PRIMARY KEY (`PROJECT_METHODOLOGIES_TYPE_ID`);

ALTER TABLE `PROJECT_STAGES`
  ADD PRIMARY KEY (`PROJECT_STAGE_ID`);

ALTER TABLE `PROJECT_STAGE_TYPES`
  ADD PRIMARY KEY (`PROJECT_STAGE_TYPE_ID`);

ALTER TABLE `PROJECT_TASKS`
  ADD PRIMARY KEY (`PROJECT_TASK_ID`);

ALTER TABLE `PROJECT_TASK_PRIORITIES`
  ADD PRIMARY KEY (`PROJECT_TASK_PRIORITY_ID`);

ALTER TABLE `PROJECT_TASK_STATUSES`
  ADD PRIMARY KEY (`PROJECT_TASK_STATUS_ID`);

ALTER TABLE `PROJECT_TASK_TYPES`
  ADD PRIMARY KEY (`PROJECT_TASK_TYPE_ID`);

ALTER TABLE `PROJECT_TYPES`
  ADD PRIMARY KEY (`PROJECT_TYPE_ID`);

ALTER TABLE `PROJECT_TYPE_KINDS`
  ADD PRIMARY KEY (`PROJECT_TYPE_KIND_ID`);

ALTER TABLE `PROJECT_TYPE_NAMES`
  ADD PRIMARY KEY (`PROJECT_TYPE_NAME_ID`);

INSERT INTO `CUSTOMERS` (`CUSTONER_ID`, `CUSTOMER_FIRST_NAME`, `CUSTOMER_LAST_NAME`, `CUSTOMER_MIDDLE_NAME`, `ORGANIZATION_ID`, `CUSTOMER_TYPE_ID`) VALUES
(3, 'Имя заказчика', 'Фамилия заказчика', 'Отчество заказчика', 4, 4);

INSERT INTO `CUSTOMER_TYPES` (`CUSTOMER_TYPE_ID`, `CUSTOMER_TYPE_NAME`) VALUES
(4, 'Тип заказчика 1');

INSERT INTO `ORGANIZATIONS` (`ORGANIZATION_ID`, `ORGANIZATION_NAME`, `ORGANIZATION_MANAGEMENT_TYPE_ID`) VALUES
(4, 'Первая организация', 2);

INSERT INTO `ORGANIZATION_MANAGEMENT_TYPES` (`ORGANIZATION_MANAGEMENT_TYPE_ID`, `ORGANIZATION_MANAGEMENT_TYPE_NAME`) VALUES
(2, 'Какой-то тип управления');