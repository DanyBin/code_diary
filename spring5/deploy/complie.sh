#!/usr/bin/env bash
mvn -U clean dependency:copy-dependencies -DoutputDirectory=./target/lib package -Dmaven.test.skip=true