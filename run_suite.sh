#!/usr/bin/env bash
mvn clean verify -Dcucumber.filter.tags="@testSuite"