Vagrant.configure("2") do |config|

  #This Bit configured vagrant to automatically start the VM with the gui
  # Vagrant starts boxes headless by default
  config.vm.provider "virtualbox" do |v|
  v.memory = 1024 * 8
  v.cpus = 2
  v.gui = true
  end

  Vagrant.configure("2") do |config|

  end
  #This is the name of the vm you will see in the Virtualbox client.
  config.vm.define "school"

  config.vm.box = "peru/ubuntu-19.04-desktop-amd64"
  config.vm.box_version = "20190905.01"

  #Bootstrapping for the VM
  config.vm.provision "shell", path: "script.sh"

  # This syncs the /synced directory with the /synced directory on the vm.
  # DO NOT sync this in the same directory as the vagrant file.
  # the .vagrant file contains the default private keys which will mess with your ability to ssh to it
  config.vm.synced_folder "./synced", "/synced"


end
