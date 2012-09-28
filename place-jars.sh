#!/bin/bash

MCCLIENTDIR="/home/mike/minecraft-1.3.2-fml-251/mods"
MCSERVERDIR="/home/mike/mc-server-fml-251/mods"
JARDIR="/home/mike/projects/reptiles-src-1.3.2"
JAR="reptiles-1.3.2-fml-251.jar"

cp -f "$JARDIR/$JAR" $MCCLIENTDIR
cp -f "$JARDIR/$JAR" $MCSERVERDIR
