#!/bin/bash

MCVER=1.4.2
FMLVER=337

MCCLIENTDIR="$HOME/minecraft-$MCVER-fml-$FMLVER/mods"
MCSERVERDIR="$HOME/mc-server-fml-$FMLVER/mods"
JARDIR="$HOME/projects/reptiles-src-1.4.x"
JAR="reptiles-$MCVER-fml-$FMLVER.jar"

cp -f "$JARDIR/$JAR" $MCCLIENTDIR
cp -f "$JARDIR/$JAR" $MCSERVERDIR
