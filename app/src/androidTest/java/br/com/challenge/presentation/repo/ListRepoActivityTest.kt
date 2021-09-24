package br.com.challenge.presentation.repo


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.challenge.MockServer
import br.com.challenge.MockServerDispatcher
import br.com.challenge.R
import br.com.challenge.domain.utils.ConstantsUtil
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ListRepoActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(ListRepoActivity::class.java)

    companion object {
        var textTitleToolbar = "Challenge"
        var textTitleValueCardView = "okhttp"
        var textTitleNameAuthor = "square"
    }

    @Before
    fun init() {
        MockServer.server.dispatcher = MockServerDispatcher().RequestDispatcher()
        MockServer.server.url(ConstantsUtil.BASE_URL_MOCK)
    }

    @Test
    fun listRepoActivityTest() {
        val textView = onView(
            allOf(
                withText(textTitleToolbar),
                withParent(
                    allOf(
                        withId(R.id.toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText(textTitleToolbar)))

        val viewGroup = onView(
            allOf(
                withId(R.id.parentWinner),
                withParent(
                    allOf(
                        withId(R.id.card_view),
                        withParent(withId(R.id.recycler_list_repo))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))

        val imageButton = onView(
            allOf(
                withId(R.id.image_avatar),
                withParent(
                    allOf(
                        withId(R.id.parentWinner),
                        withParent(withId(R.id.card_view))
                    )
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.textview_name_repo), withText(textTitleValueCardView),
                withParent(
                    allOf(
                        withId(R.id.parentWinner),
                        withParent(withId(R.id.card_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText(textTitleValueCardView)))

        val imageButton2 = onView(
            allOf(
                withId(R.id.img_fork),
                withParent(
                    allOf(
                        withId(R.id.parentWinner),
                        withParent(withId(R.id.card_view))
                    )
                ),
                isDisplayed()
            )
        )
        imageButton2.check(matches(isDisplayed()))

        val imageButton3 = onView(
            allOf(
                withId(R.id.img_star),
                withParent(
                    allOf(
                        withId(R.id.parentWinner),
                        withParent(withId(R.id.card_view))
                    )
                ),
                isDisplayed()
            )
        )
        imageButton3.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.textview_name_author), withText(textTitleNameAuthor),
                withParent(
                    allOf(
                        withId(R.id.parentWinner),
                        withParent(withId(R.id.card_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText(textTitleNameAuthor)))

        val textView4 = onView(
            allOf(
                withId(R.id.textview_name_author), withText(textTitleNameAuthor),
                withParent(
                    allOf(
                        withId(R.id.parentWinner),
                        withParent(withId(R.id.card_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText(textTitleNameAuthor)))
    }

    @After
    fun tearDown() {
        MockServer.server.shutdown()
    }
}
