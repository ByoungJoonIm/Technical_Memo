#!/bin/bash

python3 manage.py runserver localhost:8000 2>> error.log >> log.log &
