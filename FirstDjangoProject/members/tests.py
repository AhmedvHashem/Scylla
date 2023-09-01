from django.test import TestCase

from members.models import Member


class EmbedVariableTests(TestCase):
    @classmethod
    def setUpTestData(cls):
        cls.member = Member(first_name="Ahmed", last_name="Hashem")
        cls.member.save()

    def test_base_fields(self):
        self.assertTrue(self.member.id)  # type: ignore
        self.assertTrue(self.member.slug)
        self.assertTrue(self.member.first_name)
        self.assertTrue(self.member.last_name)
