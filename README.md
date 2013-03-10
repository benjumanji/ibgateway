ibgateway
=========

Auto starting code for the interactive brokers gateway service. Some code blatantly ripped from the ib controller project. Full credit to them.

Right now the code builds and runs with gradle, and gradle knows how to generate startup scripts using installApp. The plan is to add a systemd unit file and some fabric scripts for local and remote installs, and worry free start up and shutdown.

Pull requests welcome.
