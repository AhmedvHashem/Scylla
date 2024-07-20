from django.conf import settings
from django.db import connection, reset_queries

settings.DEBUG = True


def num_queries(reset=True):
    print(len(connection.queries))
    if reset:
        reset_queries()
