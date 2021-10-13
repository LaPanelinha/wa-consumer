package dev.gabrielsancho.wpconsumer.domain

class Button {
    lateinit var id: String
    lateinit var text: String
}

class Row {
    lateinit var title: String
    lateinit var description: String
    lateinit var rowId: String
}

class Section {
    lateinit var title: String
    lateinit var rows: List<Row>
}
