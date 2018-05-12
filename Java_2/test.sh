#!/usr/bin/env bash

# remove all compiled artifacts
rm -rf target/classes

# compile the files
./compile.sh
return_val=$?
if [ "$return_val" != "0" ]; then
  echo "Failed to compile"
  exit $return_val
fi

# invoke the tester
java -cp target/classes:homework2-1.0.jar edu.nyu.cs9053.homework2.Tester
return_val=$?
if [ "$return_val" != "0" ]; then
  echo "Failed when executing "
  exit $return_val
fi
echo "Good luck! Files compiled and tests ran successfully!"
