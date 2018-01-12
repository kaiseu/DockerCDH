#!/bin/bash
service ntp start
service ssh start
PASSWD=123456
expect << EOF
  spawn passwd
  expect "Enter new UNIX password:"
  send "${PASSWD}\r"
  expect "Retype new UNIX password:"
  send "${PASSWD}\r"
  expect eof;
EOF
localedef -v -c -i en_US -f UTF-8 en_US.UTF-8
bash
