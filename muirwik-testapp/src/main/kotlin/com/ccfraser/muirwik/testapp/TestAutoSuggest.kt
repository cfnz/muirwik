package com.ccfraser.muirwik.testapp

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mIconButton
import kotlinx.css.flexGrow
import kotlinx.css.maxWidth
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv


class TestAutoSuggest : RComponent<RProps, RState>() {
    private val suggestions = arrayListOf(
        "Afghanistan",
        "Aland Islands",
        "Albania",
        "Algeria",
        "American Samoa",
        "Andorra",
        "Angola",
        "Anguilla",
        "Antarctica",
        "Antigua and Barbuda",
        "Argentina",
        "Armenia",
        "Aruba",
        "Australia",
        "Austria",
        "Azerbaijan",
        "Bahamas",
        "Bahrain",
        "Bangladesh",
        "Barbados",
        "Belarus",
        "Belgium",
        "Belize",
        "Benin",
        "Bermuda",
        "Bhutan",
        "Bolivia, Plurinational State of",
        "Bonaire, Sint Eustatius and Saba",
        "Bosnia and Herzegovina",
        "Botswana",
        "Bouvet Island",
        "Brazil",
        "British Indian Ocean Territory",
        "Brunei Darussalam")

//    fun renderInput(rBuilder: RBuilder) {
//        const { InputProps, classes, ref, ...other } = inputProps;

//        rBuilder.mTextField()
//        embed = TextField()
//        return (
//        <TextField
//        InputProps={{
//            inputRef: ref,
//            classes: {
//            root: classes.inputRoot,
//        },
//            ...InputProps,
//        }}
//        {...other}
//        />
//        );
//    }

    /*

    function renderSuggestion({ suggestion, index, itemProps, highlightedIndex, selectedItem }) {
        const isHighlighted = highlightedIndex === index;
        const isSelected = (selectedItem || '').indexOf(suggestion.label) > -1;

        return (
        <MenuItem
        {...itemProps}
        key={suggestion.label}
        selected={isHighlighted}
        component="div"
        style={{
            fontWeight: isSelected ? 500 : 400,
        }}
        >
        {suggestion.label}
        </MenuItem>
        );
    }
    renderSuggestion.propTypes = {
        highlightedIndex: PropTypes.number,
        index: PropTypes.number,
        itemProps: PropTypes.object,
        selectedItem: PropTypes.string,
        suggestion: PropTypes.shape({ label: PropTypes.string }).isRequired,
    };

    function getSuggestions(inputValue) {
        let count = 0;

        return suggestions.filter(suggestion => {
            const keep =
            (!inputValue || suggestion.label.toLowerCase().indexOf(inputValue.toLowerCase()) !== -1) &&
                    count < 5;

            if (keep) {
                count += 1;
            }

            return keep;
        });
    }

    class DownshiftMultiple extends React.Component {
        state = {
            inputValue: '',
            selectedItem: [],
        };

        handleKeyDown = event => {
            const { inputValue, selectedItem } = this.state;
            if (selectedItem.length && !inputValue.length && keycode(event) === 'backspace') {
                this.setState({
                    selectedItem: selectedItem.slice(0, selectedItem.length - 1),
                });
            }
        };

        handleInputChange = event => {
            this.setState({ inputValue: event.target.value });
        };

        handleChange = item => {
            let { selectedItem } = this.state;

            if (selectedItem.indexOf(item) === -1) {
                selectedItem = [...selectedItem, item];
            }

            this.setState({
                inputValue: '',
                selectedItem,
            });
        };

        handleDelete = item => () => {
            const selectedItem = [...this.state.selectedItem];
            selectedItem.splice(selectedItem.indexOf(item), 1);

            this.setState({ selectedItem });
        };

        render() {
            const { classes } = this.props;
            const { inputValue, selectedItem } = this.state;

            return (
            <Downshift inputValue={inputValue} onChange={this.handleChange} selectedItem={selectedItem}>
                {({
                    getInputProps,
                    getItemProps,
                    isOpen,
                    inputValue: inputValue2,
                    selectedItem: selectedItem2,
                    highlightedIndex,
                }) => (
                    <div className={classes.container}>
                            {renderInput({
                                fullWidth: true,
                                classes,
                                InputProps: getInputProps({
                                startAdornment: selectedItem.map(item => (
                                <Chip
                                key={item}
                                tabIndex={-1}
                                label={item}
                                className={classes.chip}
                                onDelete={this.handleDelete(item)}
                                />
                                )),
                                onChange: this.handleInputChange,
                                onKeyDown: this.handleKeyDown,
                                placeholder: 'Select multiple countries',
                                id: 'integration-downshift-multiple',
                            }),
                            })}
                    {isOpen ? (
                        <Paper className={classes.paper} square>
                        {getSuggestions(inputValue2).map((suggestion, index) =>
                            renderSuggestion({
                                suggestion,
                                index,
                                itemProps: getItemProps({ item: suggestion.label }),
                                highlightedIndex,
                                selectedItem: selectedItem2,
                            }),
                            )}
                        </Paper>
                        ) : null}
                    </div>
                    )}
            </Downshift>
            );
        }
    }

    DownshiftMultiple.propTypes = {
        classes: PropTypes.object.isRequired,
    };

    const styles = theme => ({
        root: {
            flexGrow: 1,
            height: 250,
        },
        container: {
            flexGrow: 1,
            position: 'relative',
        },
        paper: {
            position: 'absolute',
            zIndex: 1,
            marginTop: theme.spacing.unit,
            left: 0,
            right: 0,
        },
        chip: {
            margin: `${theme.spacing.unit / 2}px ${theme.spacing.unit / 4}px`,
        },
        inputRoot: {
            flexWrap: 'wrap',
        },
    });

    function IntegrationDownshift(props) {
        const { classes } = props;

        return (
        <div className={classes.root}>
        <Downshift>
                {({ getInputProps, getItemProps, isOpen, inputValue, selectedItem, highlightedIndex }) => (
                    <div className={classes.container}>
                            {renderInput({
                                fullWidth: true,
                                classes,
                                InputProps: getInputProps({
                                placeholder: 'Search a country (start with a)',
                                id: 'integration-downshift-simple',
                            }),
                            })}
                    {isOpen ? (
                        <Paper className={classes.paper} square>
                        {getSuggestions(inputValue).map((suggestion, index) =>
                            renderSuggestion({
                                suggestion,
                                index,
                                itemProps: getItemProps({ item: suggestion.label }),
                                highlightedIndex,
                                selectedItem,
                            }),
                            )}
                        </Paper>
                        ) : null}
                    </div>
                    )}
        </Downshift>
        <DownshiftMultiple classes={classes} />
        </div>
        );
    }

    IntegrationDownshift.propTypes = {
        classes: PropTypes.object.isRequired,
    };

    export default withStyles(styles)(IntegrationDownshift);




*/












    override fun RBuilder.render() {

        styledDiv {
            css { maxWidth = 800.px }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(MAppBarColor.default, MAppBarPosition.static) {
                    mToolbar {
                        mTypography("Title", variant = MTypographyVariant.h6, color = MTypographyColor.inherit)
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.static) {
                    mToolbar {
                        mIconButton("menu", color = MColor.inherit)
                        mTypography("Title", variant = MTypographyVariant.h6, color = MTypographyColor.inherit) {
                            css { flexGrow = 1.0 }
                        }
                    }
                }
            }

            styledDiv {
                css { flexGrow = 1.0; padding(2.spacingUnits) }

                mAppBar(position = MAppBarPosition.static) {
                    mToolbar {
                        mIconButton("menu", color = MColor.inherit)
                        mToolbarTitle("Toolbar Title One Liner")
                    }
                }
            }
        }
    }
}

fun RBuilder.testAutoSuggest() = child(TestAutoSuggest::class) {}
