NAME=mqadventure
VERSION=1.0

echo "Clean up"
rm -rf rpmbuild/SOURCES/*
rm -rf rpmbuild/BUILD/*
rm -rf rpmbuild/BUILDROOT/*
rm -rf rpmbuild/RPMS/*
rm -rf rpmbuild/SRPMS/*
rm -f rpmbuild/SPECS/mqadventure.spec
rm -rf mqadventure-*
rm -f *.tgz

echo "Prepare"
cp -f rpmbuild/SPECS/mqadventure.spec.org rpmbuild/SPECS/mqadventure.spec
FULLNAME="$NAME-$VERSION"

echo "Make TGZ file"
echo "FULLNAME IS $FULLNAME/opt/mqadventure"
mkdir -p $FULLNAME/opt/mqadventure

cp target/MqAdventure-1.0-jar-with-dependencies.jar $FULLNAME/opt/mqadventure
cp files/mqadventure.sh $FULLNAME/opt/mqadventure
tar -cvf $FULLNAME.tgz $FULLNAME
echo "Made $FULLNAME.tgz"

yum -y install rpm-build
echo "prepare package"
sed -i s/__version__/$VERSION/g rpmbuild/SPECS/mqadventure.spec
mkdir -p  rpmbuild/SOURCES rpmbuild/BUILD rpmbuild/RPMS rpmbuild/SRPMS
cp $FULLNAME.tgz rpmbuild/SOURCES
echo "build rpm"
cd rpmbuild
ls -alR
rpmbuild -v --define "_topdir `pwd`" --define "dist .linux" -bb SPECS/mqadventure.spec


