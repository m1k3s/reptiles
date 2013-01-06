#!/bin/bash

echo "== Creating jars =="

KEYPASS=
STOREPASS=

MCVER=1.4.6
FMLVER=471

DATE=`date +%Y%m%d`

MCMODDATA="
[\n
{\n
  \"modid\": \"ReptileMod\",\n
  \"name\": \"Reptile Mod\",\n
  \"description\": \"Reptile mod adds monitor lizards, turtles, iguanas, chameleons, and crocodiles. Komodo Dragons! Man-eating Crocodiles! And cute little turtles.\",\n
  \"version\": \"$DATE\",\n
  \"mcversion\": \"$MCVER\",\n
  \"url\": \"\",\n
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

JAR="reptiles-$MCVER-forge-$FMLVER.jar"

echo -e "Main-Class: reptiles.common.Reptiles\nClass-Path: $JAR\n" > $RDIR/manifest.txt

rm -f $JAR
jar -cfm $JAR manifest.txt reptiles/ mcmod.info mob/ sound/ logo/

echo "> signing $JAR"
jarsigner -storetype pkcs12 -keystore $HOME/.keystore -keypass $KEYPASS -storepass $STOREPASS $JAR cracked

echo " - Mod build complete - `date "+%H:%M:%S"`"

