from django.http import HttpResponse
from django.template import loader

from members.models import Member


def members(request):
    # template = loader.get_template("members.html")
    # return HttpResponse(template.render())
    return HttpResponse(Member.objects.all().values())


def details(request, slug):
    mymember = Member.objects.get(slug=slug)
    return HttpResponse(mymember.first_name)
