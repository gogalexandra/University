
def proiecte_ord_desc(self):
    result = sorted(??self.repo, key=lambda x: x.nrOreNecesare, reverse=True)
    for prj in result:
        print(prj)


    def search_activity(self, info):
        """
        Creates a list with all activities in which is found the input that is given by the user
        :param info: the input that the user gives
        :return:
        """
        search_list = []
        for a in self.activities:
            if info.casefold() in a.time.casefold() or info.casefold() in str(
                    a.date).casefold() or info.casefold() in a.description.casefold():
                search_list.append(a)
        return search_list