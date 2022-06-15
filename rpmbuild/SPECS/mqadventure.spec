Name:           mqadventure
Version:        1.0
Release:        1%{?dist}
Summary:        The MQ Adventure
License:        My Software
Source0:        %{name}-%{version}.tgz


%description
MQ Adventure.


%global debug_package %{nil}


%prep
%setup -q


%build


%install
rm -rf $RPM_BUILD_ROOT
mkdir -p $RPM_BUILD_ROOT/opt/mqadventure
ls -R
cp -r $RPM_BUILD_DIR/%{name}-%{version}/*  $RPM_BUILD_ROOT


%post
if [ "$1" = "2" ] ; then
  echo "Upgrade"
fi
if [ "$1" = "1" ] ; then
  echo "Fresh install"
  ln -sf /opt/mqadventure/mqadventure.sh /usr/bin/mqadventure
fi


%files
%defattr(-,root,root)
%attr(755,root,root) /opt/mqadventure/
%attr(644,root,root) /opt/mqadventure/MqAdventure-1.0-jar-with-dependencies.jar
%attr(755,root,root) /opt/mqadventure/mqadventure.sh


%preun
if [ "$1" = "1" ] ; then
  echo "Upgrade"
fi
if [ "$1" = "0" ] ; then
  echo "Uninstall"
fi


%postun
rm -rf /opt/mqadventure
