#!/bin/bash

echo "== Creating jars =="

MCVER=1.4.2
FMLVER=337

DATE=`date +%Y%m%d`

MCMODDATA="
[\n
{\n
  \"modid\": \"ReptileMod\",\n
  \"name\": \"Reptile Mod\",\n
  \"description\": \"Reptile mod adds monitor lizards, turtles, iguanas, chameleons, and crocodiles. Komodo Dragons! Man-eating Crocodiles! And cute little turtles.\",\n
  \"version\": \"$DATE\",\n
  \"mcversion\": \"$MCVER\",\n
  \"url\": \"http://www.minecraftforum.net/topic/585469-132modloader-crackedeggs-mods-reptiles-parachute-updated-08272012/\",\n
  \"updateUrl\": \"\",\n
  \"authors\": [\n
    \"crackedEgg\"\n
  ],\n
  \"credits\": \"Authored by crackedEgg\",\n
  \"logoFile\": \"/logo/reptileLogo.png\",\n
  \"screenshots\": [],\n
  \"parent\": \"\",\n
  \"dependencies\": []\n
}\n
]"

echo "> copying files"

REOBF="reobf/minecraft/"
cd $REOBF

RDIR="$HOME/projects/reptiles-src-1.4.x"

rm -f $RDIR/reptiles/common/*.class
rm -f $RDIR/reptiles/client/*.class

cp -R reptiles/ $RDIR
echo -e $MCMODDATA > $RDIR/mcmod.info

echo "> making mod jar file"

cd $RDIR

JAR="reptiles-$MCVER-fml-$FMLVER.jar"

rm -f $JAR
jar -cf $JAR reptiles/  mcmod.info mob/ sound/ logo/

echo " - Mod build complete"

