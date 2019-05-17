#!/bin/bash

python3 manage.py runserver localhost:9000 2>> error.log >> log.log &
