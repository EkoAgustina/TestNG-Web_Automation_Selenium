browser=${1}

cucumberTags='@addProduct'

export browser=${browser}
export cucumberTags=${cucumberTags}
mvn clean test -Dcucumber.filter.tags=${cucumberTags}
