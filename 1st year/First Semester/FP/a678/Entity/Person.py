class PersonException(Exception):
    def __init__(self, msg):
        self._msg = msg


class PersonValidationException(PersonException):
    def __init__(self, error_list):
        self._errors = error_list

    def __str__(self):
        result = ''

        for er in self._errors:
            result += er
            result += '\n'
        return result


class PersonValidator:

    @staticmethod
    def is_id_valid(person_id):
        if not isinstance(person_id, int) or person_id < 1:
            return False
        else:
            return True

    @staticmethod
    def is_name_valid(name):
        return not any(char.isdigit() for char in name)

    @staticmethod
    def is_phone_number_valid(phone_number):
        if not any(char.isdigit() for char in str(phone_number)) or (phone_number[0] != '0' or phone_number[1] != '7') or len(str(phone_number)) != 10:
            return False
        else:
            return True

    def is_person_valid(self, person):
        errors = []
        if not self.is_id_valid(person._person_id):
            errors.append('!!! Invalid id !!!')
        if not self.is_name_valid(person.name):
            errors.append('!!! Invalid name !!!')
        if not self.is_phone_number_valid(person.phone_number):
            errors.append('!!! Invalid phone number !!!')

        if len(errors) > 0:
            raise PersonValidationException(errors)


class Person:

    def __init__(self, person_id, name, phone_number):

        if not isinstance(person_id, int):
            raise PersonException('Id should be form with digits')

        if len(str(phone_number)) == str(10):
            #print(len(str(phone_number)))
            raise PersonException('A phone number must have 10 digits')

        if not any(char.isdigit() for char in str(phone_number)):
            raise PersonException('A phone number is form with digits')

        if phone_number[0] != '0' or phone_number[1] != '7':
            raise PersonException('A phone number starts with 07')

        if any(char.isdigit() for char in name):
            raise PersonException('Name should be a string of chars')

        self._person_id = person_id
        self._name = self.split_name(name)
        self._phone_number = phone_number

    def split_name(self, name):
        """
        Makes sure that the name begins with a capitalize letter
        :param name: the name of the person
        :return: the name with capitalize letter
        """

        names = name.strip().split(' ')
        new_person_name = ''
        for n in range(0, len(names)):
            new_person_name += names[n].strip().capitalize() + ' '
        return new_person_name.strip()

    @property
    def person_id(self):
        return self._person_id

    @property
    def name(self):
        return self._name

    @property
    def phone_number(self):
        return self._phone_number

    def __str__(self):
        """
        Transforms everything in a string to be easier at printing on the output
        :return: the object as a string
        """
        return str('Id: ' + str(self._person_id).ljust(5) + 'Name: ' + self._name.ljust(
            25) + 'Phone number: ' + self._phone_number)


