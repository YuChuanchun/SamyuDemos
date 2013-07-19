import xml.etree.ElementTree as ET

TAG = 'update'
FILE_PATH_COMMON = 'common.py'
FILE_PATH_CONFIG = 'config.xml'

def update():
    try:
        output = open(FILE_PATH_COMMON, 'w')
    except IOError:
        print(TAG, 'open common.py error')
    tree = ET.parse(FILE_PATH_CONFIG)
    root = tree.getroot()
    for child in root.findall('public'):
        if child.get('type') == 'string':
            public = "PUBLIC_" + child.get("name").upper() + " = '" + child.text + "'\n"
        elif child.get('type') == 'boolean':
            public = "PUBLIC_" + child.get("name").upper() + " = '" + child.text + "'\n"
        output.write(public)

    for child in root.findall('private'):
        prefix = 'PRIVATE_' + child.get('name').upper() + "_"
        for item in child.findall('item'):
            if item.get('type') == 'string':
                private = prefix + item.get('name').upper() + " = '" + item.text + "'\n"
            elif item.get('type') == 'boolean':
                if item.text.lower() == 'true':
                    private = prefix + item.get('name').upper() +  " = True\n"
                else:
                    private = prefix + item.get('name').upper() +  " = False\n"
            output.write(private)
update()