class ActivityException(Exception):
    def __init__(self, msg):
        self._msg = msg


class ActivityValidationException(ActivityException):
    def __init__(self, error_list):
        self._errors = error_list

    def __str__(self):
        result = ''

        for er in self._errors:
            result += er
            result += '\n'
        return result


class ActivityValidator:

    @staticmethod
    def is_id_valid(activity_id):
        if not isinstance(activity_id, int) or activity_id < 1:
            return False
        else:
            return True

    @staticmethod
    def is_time_valid(time):
        ok = True
        time_info = str(time).split('-')
        start_hour = time_info[0].strip().split(':')
        end_hour = time_info[1].strip().split(':')
        if int(start_hour[0]) > int(end_hour[0]) or \
                (int(start_hour[0]) == int(end_hour[0]) and int(start_hour[1]) >= int(end_hour[1])):
            ok = False
        if not ok or any(c.isalpha() for c in str(time)):
            return False
        else:
            return True

    @staticmethod
    def is_date_valid(date):
        if any(c.isalpha() for c in str(date)):
            return False
        else:
            return True

    def is_activity_valid(self, activity):
        errors = []
        if not self.is_id_valid(activity.activity_id):
            errors.append('!!! Id invalid !!!')
        if not self.is_time_valid(activity.time):
            errors.append('!!! Invalid time !!!')
        if not self.is_date_valid(activity.date):
            errors.append('!!! Invalid date !!!')

        if len(errors) > 0:
            raise ActivityValidationException(errors)


class Activity:

    def __init__(self, activity_id, persons_id: list, date, time, description):

        if not isinstance(activity_id, int):
            raise ActivityException('Id should be form with digits')

        if activity_id < 1:
            raise ActivityException('Id can not be smaller than 1')

        if any(c.isalpha() for c in str(date)):
            raise ActivityException('Date should be form with digits')

        if any(c.isalpha() for c in str(time)):
            raise ActivityException('Time should be form with digits')

        time_info = str(time).split('-')
        start_hour = time_info[0].strip().split(':')
        end_hour = time_info[1].strip().split(':')
        if int(start_hour[0]) > int(end_hour[0]) or \
                (int(start_hour[0]) == int(end_hour[0]) and int(start_hour[1]) >= int(end_hour[1])):
            raise ActivityException('The start hour can not be bigger than end hour')

        self._activity_id = activity_id
        self._persons_id = persons_id
        self._persons_id: list
        self._date = date
        self._time = time
        self._description = description

    @property
    def activity_id(self):
        return self._activity_id

    @property
    def persons_id(self) -> list:
        return self._persons_id

    @property
    def date(self):
        return self._date

    @property
    def time(self):
        return self._time

    @property
    def description(self):
        return self._description

    def __str__(self):
        """
        Converts everything into a string to be easier to print
        :return: a string
        """

        return str(
            'Activity id: ' + str(self._activity_id).ljust(5) + ', Persons: ' + str(self._persons_id).ljust(
                5) + ', from date: '
            + str(self._date).ljust(5) + ', from-to: ' + str(self._time).ljust(
                5) + ', description: ' + self._description)


