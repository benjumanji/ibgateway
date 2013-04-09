import os.path

from fabric.api import run, cd, env, sudo, put
from fabric.contrib.files import exists


project_name = 'ibgateway'
git_path = 'git clone git://github.com/benjumanji/%s.git' % project_name

usr_share = '/usr/local/share/%s' % project_name

def dev():
    env.user = 'ib'
    env.hosts = ['localhost']

def prod():
    env.user = 'ben'
    env.hosts = ['batcave.artfuldodge.io']

def build():
    run('gradle installApp')

def clean():
    run('gradle clean')

def initialise():
    run(git_path)

def pull():
    run('git pull origin master')

def copy_ini():
    ini = os.path.expanduser('~/.ib/ib.ini')
    run('mkdir -p /home/%s/.ib' % env.user)
    put(ini, '/home/%s/.ib/ib.ini' % env.user)
    put('~/.ib/settings', '~/.ib')

def copy_unit():
    path = '/usr/local/lib/systemd/system'
    if not exists(path):
        sudo('mkdir -p %s' % path)
    with cd('systemd'):
        sudo('cp *.service %s' % path)

def install():
    with cd('build/install/%s' % project_name):
        if not exists(usr_share):
            sudo('mkdir %s' % usr_share)
        sudo('cp -r bin/ lib/ %s' % usr_share)
        if not exists('/usr/bin/ibgateway'):
            sudo('ln -s /usr/local/share/ibgateway/bin/ibgateway \
                 /usr/bin/ibgateway')
        copy_ini()

def deploy():
    build_dir = '/home/%s/build/' % env.user
    if not exists(build_dir):
        run('mkdir %s' % build_dir)
    with cd(build_dir):
        if not exists(project_name):
            initialise()
        with cd(project_name):
            pull()
            build()
            install()
            copy_unit()
            clean()







