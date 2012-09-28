#!/bin/bash

MCCLIENTDIR="$HOME/minecraft-1.3.2-fml-251/mods"
MCSERVERDIR="$HOME/mc-server-fml-251/mods"
JARDIR="$HOME/projects/reptiles-src-1.3.2"
JAR="reptiles-1.3.2-fml-251.jar"

cp -f "$JARDIR/$JAR" $MCCLIENTDIR
cp -f "$JARDIR/$JAR" $MCSERVERDIR
